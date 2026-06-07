package com.payflow.transaction.service;

import com.netflix.discovery.converters.Auto;
import com.payflow.transaction.dto.AccountResponse;
import com.payflow.transaction.entity.Transaction;
import com.payflow.transaction.enums.TransactionStatus;
import com.payflow.transaction.feignClient.AccountClient;
import com.payflow.transaction.feignClient.LedgerClient;
import com.payflow.transaction.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountClient accountClient;

    @Autowired
    private LedgerClient ledgerClient;

    @Transactional
    public String transfer(Long senderId, Long receiverId, BigDecimal amount){

        if (senderId.equals(receiverId)) {
            throw new RuntimeException("Same user not allowed");
        }

        Transaction txn = new Transaction();
        txn.setSenderId(senderId);
        txn.setReceiverId(receiverId);
        txn.setAmount(amount);
        txn.setStatus(TransactionStatus.PENDING);

        AccountResponse sender = accountClient.getAccount(senderId);
        AccountResponse receiver = accountClient.getAccount(receiverId);

        if (sender.getBalance().compareTo(amount) < 0){
            throw new RuntimeException("Insufficient amount!");
        }
        try {
            accountClient.debit(sender.getUserId(),amount);
            accountClient.credit(receiver.getUserId(),amount);
            txn.setStatus(TransactionStatus.SUCCESS);
            ledgerClient.recordEntries(sender.getUserId(),receiver.getUserId(),amount,txn.getReferenceId());
        }
        catch (Exception e){
            try{
                accountClient.credit(sender.getUserId(),amount);
            }catch (Exception ex){
            }
            txn.setStatus(TransactionStatus.FAILED);
        }
        transactionRepository.save(txn);
        return txn.getReferenceId();
    }
}
