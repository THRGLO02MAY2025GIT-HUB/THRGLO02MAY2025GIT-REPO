
### Lab Activity 1: Exploring Private Methods in Interfaces in Java

#### Duration: 30 minutes
In this lab, you will explore the usage of private methods in interfaces in Java. These methods help in encapsulating reusable logic within the interface itself. Follow the scenarios below and write the corresponding code in the `main` method.

## Scenarios

### 1. Demonstrating Private Static Methods
Write code to demonstrate the use of the `generateAuditId` private static method. Call the `processWithAudit` default method and observe how the audit ID is generated internally.

### 2. Demonstrating Private Instance Methods
Write code to demonstrate the use of private instance methods like `validateTransaction`, `validateAmount`, and `validateCurrency`. Attempt to process a transaction with invalid data and handle the exceptions.

### 3. Demonstrating Supported Currencies
Write code to process a transaction with a supported currency and another with an unsupported currency. Observe how the private static method `getSupportedCurrencies` is used internally for validation.

### 4. Demonstrating Logging with Private Static Methods
Write code to process a transaction and observe how the `logTransaction` private static method is used internally to log the transaction details.

### 5. Implementing Custom Business Logic
Create a class that implements the `TransactionProcessor` interface. Override the `execute` method to define custom business logic for processing a transaction. Use the `processWithAudit` method to process a transaction and observe the flow.


### Steps to Complete the Lab in the main method.

1. **Create the Transaction Processor**  
    - Implement the `TransactionProcessor` interface in a class, such as `CustomTransactionProcessor`.  
    - Override the `execute` method to define custom business logic for processing a transaction.

2. **Create the Transaction**  
    - Instantiate the `Transaction` class with appropriate values for `amount`, `currency`, and `description`.  
    - Example: `Transaction tx = new Transaction(new BigDecimal("100.00"), "USD", "Payment for services");`.

3. **Process the Transaction with Audit**  
    - Use the `processWithAudit` method of the `TransactionProcessor` implementation to process the transaction.  
    - Observe how private methods like `generateAuditId`, `validateTransaction`, and `logTransaction` are used internally.

4. **Explore Additional Possibilities**  
    - Implement the `TransactionProcessor` interface in multiple classes to explore how abstract, private, static, and default methods work.  
    - Experiment with different scenarios, such as processing transactions with invalid data or unsupported currencies, to understand the behavior of private methods in interfaces.


## Partial Code Snippet

```java
package m4privatemethodsininterfaces;

import java.math.BigDecimal;

public class Transaction {
    private final BigDecimal amount;
    private final String currency;
    private final String description;

    public Transaction(BigDecimal amount, String currency, String description) {
        this.amount = amount;
        this.currency = currency;
        this.description = description;
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getDescription() {
        return String.format("%s, %s, (%s)", amount, currency, description);
    }
}

package m4privatemethodsininterfaces;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;
@FunctionalInterface
public interface TransactionProcessor {
    // Abstract method
    // Execute the core transaction processing logic
    // Implementation should define the specific business rules for transaction processing
    public abstract void execute(Transaction tx);
    //public abstract void execute1(Transaction tx);
    //Default method - provide common implementation while allowing override if needed
    default void processWithAudit(Transaction tx) {
        String auditId = generateAuditId();
        validateTransaction(tx);
        logTransaction(tx, auditId);
        execute(tx);
    }

    // Private static method - utility methods that does not need instance states
    private static String generateAuditId() {
        return "AUDIT-" + UUID.randomUUID().toString();
    }

    // Private method - internal helper method not meant to be exposed to the implementing class
    private void validateTransaction(Transaction tx) {
        if (tx == null) {
            throw new IllegalArgumentException("Transaction cannot be null");
        }
        validateAmount(tx.getAmount());
        validateCurrency(tx.getCurrency());
    }
    // Private method - internal validation logic not meant to be exposed
    private void validateAmount(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
    }
    // Private method - internal validation logic not meant to be exposed
    private void validateCurrency(String currency) {
       if(!getSupportedCurrencies().contains(currency)) {
           throw new UnsupportedOperationException("Currencies are not supported " + currency);
       }
    }
    //Provides set of supported currency codes
    // Private static method - utility methods that does not need instance state
    private static Set<String> getSupportedCurrencies(){
        return Set.of("EUR", "GBP", "USD", "JPY");
    }

    //Private method - internal logging functionality not meant to be exposed
    private static void logTransaction(Transaction tx, String auditId) {
        System.out.printf("Processing transaction [%s] : %s%n", auditId, tx);
    }
}

package m4privatemethodsininterfaces;


// Lab : Perform implementation of private methods in interfaces
public class TransactionProcessorDemo {
    public static void main(String[] args) {
        // Step 1 : Create the transaction processor
        // Step 2 : Create the transaction
        // Step 3 : Process the transaction with Audit
        // Step 4 : Think of other possibilities such as implementing the interfaces through classes to figure out how abstract, private, static, default methods work.
    }
}
```

###  Lab Activity 2: Explore your existing code-base in your current project at your organization and identify areas where private methods in interfaces can be used to encapsulate reusable logic and improve code maintainability.

