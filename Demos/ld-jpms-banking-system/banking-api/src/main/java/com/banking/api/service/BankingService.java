package com.banking.api.service;

import com.banking.api.model.Account;
import java.math.BigDecimal;
import java.util.Optional;

public interface BankingService {
    Account createAccount(String customerId, Account.AccountType type);
    Optional<Account> getAccount(String accountId);
    void transfer(String fromAccountId, String toAccountId, BigDecimal amount);
    void listAccounts(String customerId);
}