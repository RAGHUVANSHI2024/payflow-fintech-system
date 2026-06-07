package com.payflow.transaction.controller;

import com.payflow.transaction.service.TransactionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping
public class TransactionController {

    private TransactionService transactionService;

    @PostMapping("/transfer")
    public String transfer(@RequestParam Long senderId,
                           @RequestParam Long receiverId,
                           @RequestParam BigDecimal amount) {

        transactionService.transfer(senderId, receiverId, amount);
        return "Transaction Successful";
    }
}
