package com.payflow.account.service;

import com.netflix.discovery.converters.Auto;
import com.payflow.account.dto.AccountResponse;
import com.payflow.account.entity.Account;
import com.payflow.account.repository.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    public void credit(Long userId, BigDecimal amount){
        Account account = accountRepository.findByUserId(userId)
                .orElseThrow(()-> new RuntimeException("Account not found"));

        account.setBalance(account.getBalance().add(amount));
        accountRepository.save(account);
    }

    public void debit(Long userId,BigDecimal amount){
        Account account = accountRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        if (account.getBalance().compareTo(amount)>0){
            account.setBalance(account.getBalance().subtract(amount));
        }else {
            throw new RuntimeException("Insufficient balance");
        }
        accountRepository.save(account);
    }

    public AccountResponse getAccount(Long userId){
        Account acc = accountRepository.findByUserId(userId)
                .orElseThrow(()-> new RuntimeException("Account not found!"));
        return new AccountResponse(acc.getUserId(),acc.getBalance());
    }
}
