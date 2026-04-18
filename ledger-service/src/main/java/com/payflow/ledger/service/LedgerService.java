package com.payflow.ledger.service;

import com.payflow.ledger.entity.LedgerEntry;
import com.payflow.ledger.enums.EntryType;
import com.payflow.ledger.repository.LedgerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class LedgerService {

    @Autowired
    private LedgerRepository ledgerRepository;

    public void recordEntries(Long senderAccId, Long receiverAccId, BigDecimal amount,String referenceId){

        LedgerEntry debit = new LedgerEntry();
        debit.setAccountId(senderAccId);
        debit.setAmount(amount);
        debit.setType(EntryType.DEBIT);
        debit.setReferenceId(referenceId);

        LedgerEntry credit = new LedgerEntry();
        credit.setAccountId(receiverAccId);
        credit.setAmount(amount);
        credit.setType(EntryType.CREDIT);
        credit.setReferenceId(referenceId);

        ledgerRepository.saveAll(List.of(debit,credit));
    }
}
