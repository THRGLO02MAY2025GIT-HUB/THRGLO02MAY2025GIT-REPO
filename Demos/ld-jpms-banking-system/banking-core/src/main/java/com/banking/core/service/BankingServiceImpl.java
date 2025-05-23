package com.banking.core.service;

import com.banking.api.model.Account;
import com.banking.api.service.BankingService;
import com.banking.core.model.AccountImpl;
import java.math.BigDecimal;
import java.util.*;

public class BankingServiceImpl implements BankingService {
    private final Map<String, Account> accounts = new HashMap<>();

    @Override
    public Account createAccount(String customerId, Account.AccountType type) {
        Account account = new AccountImpl(customerId, type);
        accounts.put(account.getAccountId(), account);
        return account;
    }

    @Override
    public Optional<Account> getAccount(String accountId) {
        return Optional.ofNullable(accounts.get(accountId));
    }

    @Override
    public void transfer(String fromAccountId, String toAccountId, BigDecimal amount) {
        Account fromAccount = accounts.get(fromAccountId);
        Account toAccount = accounts.get(toAccountId);

        if (fromAccount == null || toAccount == null) {
            throw new IllegalArgumentException("Invalid account ID");
        }

        try {
            fromAccount.withdraw(amount);
            toAccount.deposit(amount);
        } catch (Account.InsufficientFundsException e) {
            throw new RuntimeException("Transfer failed: " + e.getMessage());
        }
    }

    @Override
    public void listAccounts(String customerId) {
        accounts.values().stream()
            .filter(account -> account.getCustomerId().equals(customerId))
            .forEach(account -> System.out.printf("Account ID: %s, Type: %s, Balance: $%.2f%n",
                account.getAccountId(),
                account.getType(),
                account.getBalance()));
    }
}