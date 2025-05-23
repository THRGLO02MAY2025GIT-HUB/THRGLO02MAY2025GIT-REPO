package com.banking.app;

import com.banking.api.model.Account;
import com.banking.api.service.BankingService;


import java.math.BigDecimal;
import java.util.ServiceLoader;

public class BankingApplication {
    private final BankingService bankingService;

    public BankingApplication() {
        this.bankingService = ServiceLoader.load(BankingService.class)
            .findFirst()
            .orElseThrow(() -> new RuntimeException("No BankingService implementation found"));
    }

    public void demonstrateOperations() {
        // Create customer accounts
        String customerId = "CUST001";
        Account savingsAccount = bankingService.createAccount(customerId, Account.AccountType.SAVINGS);
        Account checkingAccount = bankingService.createAccount(customerId, Account.AccountType.CHECKING);

        // Perform operations
        try {
            savingsAccount.deposit(new BigDecimal("1000.00"));
            checkingAccount.deposit(new BigDecimal("500.00"));
            
            System.out.println("\nInitial account status:");
            bankingService.listAccounts(customerId);

            bankingService.transfer(savingsAccount.getAccountId(), 
                                  checkingAccount.getAccountId(), 
                                  new BigDecimal("300.00"));

            System.out.println("\nAccount status after transfer:");
            bankingService.listAccounts(customerId);

        } catch (Exception e) {
            System.err.println("Error during operations: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        System.out.println("THANK YOU SO MUCH! A LITTLE MORE TO GO! HOPE YOU HAD A WONDERFUL LEARNING! ALL THE BEST!");
        BankingApplication app = new BankingApplication();
        app.demonstrateOperations();
    }
}