package com.payflow.account.dto;

import jakarta.persistence.Column;

import java.math.BigDecimal;

public class AccountResponse {

    private Long userId;

    private BigDecimal balance;

    public AccountResponse(Long userId, BigDecimal balance) {
        this.userId = userId;
        this.balance = balance;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
