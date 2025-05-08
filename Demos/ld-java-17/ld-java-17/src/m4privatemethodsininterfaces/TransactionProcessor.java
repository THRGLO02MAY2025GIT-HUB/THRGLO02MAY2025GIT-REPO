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