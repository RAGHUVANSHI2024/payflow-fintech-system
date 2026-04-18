package com.payflow.transaction.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;

@FeignClient("ledger-service")
public interface LedgerClient {

    @PostMapping("/entries")
    void recordEntries(Long senderAccId, Long receiverAccId, BigDecimal amount,String referenceId);
}
