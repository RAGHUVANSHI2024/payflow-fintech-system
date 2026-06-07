package com.payflow.transaction.feignClient;

import com.payflow.transaction.dto.AccountResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient("account-service")
public interface AccountClient {

    @GetMapping("/acc/{userId}")
    AccountResponse getAccount(@PathVariable long userId);

    @GetMapping("/credit")
    void debit(@RequestParam Long userId,@RequestParam BigDecimal amount);

    @GetMapping("/debit")
    void credit(@RequestParam Long userId,@RequestParam BigDecimal amount);
}
