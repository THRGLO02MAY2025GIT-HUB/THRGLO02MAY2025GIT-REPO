package com.banking.api.model;

import java.math.BigDecimal;

public interface Account {
    String getAccountId();
    String getCustomerId();
    BigDecimal getBalance();
    AccountType getType();
    void deposit(BigDecimal amount);
    void withdraw(BigDecimal amount) throws InsufficientFundsException;

    enum AccountType {
        SAVINGS, CHECKING
    }

    class InsufficientFundsException extends Exception {
        public InsufficientFundsException(String insufficientFundsForWithdrawal) {
            super(insufficientFundsForWithdrawal);
        }
    }
}