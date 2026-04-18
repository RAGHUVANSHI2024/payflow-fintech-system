package com.payflow.ledger.controller;

import com.netflix.discovery.converters.Auto;
import com.payflow.ledger.service.LedgerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping
public class LedgerController {

    @Autowired
    private LedgerService ledgerService;

    @PostMapping("/entries")
    public void entries(Long sender, Long receiver, BigDecimal amount,String referenceId){
        ledgerService.recordEntries(sender,receiver,amount,referenceId);
    }
}
