package com.payflow.account.controller;

import com.payflow.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/credit")
    public ResponseEntity credit(@RequestParam Long userId,
                                 @RequestParam BigDecimal amount){
        accountService.credit(userId,amount);
        return ResponseEntity.ok("Amount credited");
    }

    public ResponseEntity debit(@RequestParam long userId,
                                @RequestParam BigDecimal amount){
        accountService.debit(userId,amount);
        return ResponseEntity.ok("Amount debited");

    }
}
