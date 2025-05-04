package f1diamond;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiamondOperatorDemo {
    public static void main(String[] args) {
        // Legacy [Pre Java 7]
        Map<String, List<Transaction>> legacyTransactions = new HashMap<>();
        legacyTransactions.put("USD",new ArrayList<Transaction>());
        // Task 1 : Add "EUR", "JPY" to the currency list.....
        // Java 7
        TransactionProcessor<Transaction> legacyProcessor = new TransactionProcessor<Transaction>() {
            @Override
            public void process(Transaction transaction) {
                System.out.println("Modern Transaction: " + transaction);
            };
        };

        // Java 9+ Diamond operator with anonymous inner class
        TransactionProcessor<Transaction> modernProcessor = new TransactionProcessor<>() {
            @Override
            public void process(Transaction transaction) {
                System.out.println("Modern Transaction: " + transaction);
            };
        };
        // Adding transaction to legacy Transactions
        legacyTransactions.get("USD").add(new Transaction("T1", BigDecimal.valueOf(100.50)));
        legacyTransactions.get("USD").add(new Transaction("T2", BigDecimal.valueOf(200.50)));
        System.out.println("Legacy Transactions: (USD)" + legacyTransactions) ;

        // Task 2 : Add transactions to legacyProcessor
        // Task 3 : Add transaction to modernProcessor

        // Exploration : Take your existing code base and see chances of make code simpler.
    }
}
// Common domain class
class Transaction {
    private String id;
    private BigDecimal amount;

    public Transaction(String id, BigDecimal amount) {
        this.id = id;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction {id=" + id + ", amount=" + amount + "}";
    }
}
//@FunctionalInterface
interface TransactionProcessor<T> {
    void process(T t);
}