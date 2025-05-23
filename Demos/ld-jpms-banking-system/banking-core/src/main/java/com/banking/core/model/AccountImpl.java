package com.banking.core.model;

import com.banking.api.model.Account;
import java.math.BigDecimal;
import java.util.UUID;

public class AccountImpl implements Account {
    private final String accountId;
    private final String customerId;
    private BigDecimal balance;
    private final AccountType type;

    public AccountImpl(String customerId, AccountType type) {
        this.accountId = UUID.randomUUID().toString();
        this.customerId = customerId;
        this.type = type;
        this.balance = BigDecimal.ZERO;
    }

    @Override
    public String getAccountId() {
        return accountId;
    }

    @Override
    public String getCustomerId() {
        return customerId;
    }

    @Override
    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public AccountType getType() {
        return type;
    }

    @Override
    public void deposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        this.balance = this.balance.add(amount);
    }

    @Override
    public void withdraw(BigDecimal amount) throws InsufficientFundsException {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if (balance.compareTo(amount) < 0) {
            throw new InsufficientFundsException("Insufficient funds for withdrawal");
        }
        this.balance = this.balance.subtract(amount);
    }
}