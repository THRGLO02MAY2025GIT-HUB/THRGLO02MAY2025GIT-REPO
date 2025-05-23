package m1diamond;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiamondOperatorDemo {
    public static void main(String[] args) {
        // Legacy [Pre Java 7]
        // Prior to Java 7, we had to specify the type of the collection explicitly.
        // This is known as "raw type" usage and can lead to type safety issues.
        // The List<Transaction> is not getting applied to List<SecureTransaction>
        // This is due to type safety issues.
        // The List<SecureTransaction> is not getting applied to List<Transaction> because of the type erasure.
        // Raw Types and Type Erasure.
        // Map<String, List<Transaction>> legacyTransactions = new HashMap<String, List<SecureTransaction>>();

        // The code below is a legacy code that uses raw types.
        Map<String, List<Transaction>> legacyTransactions = new HashMap<String, List<Transaction>>();
        legacyTransactions.put("USD",new ArrayList<Transaction>());
        
        //The code below is a legacy code that uses raw types, but the type is not specified.
        // The type is inferred from the context. This is known as "type inference".

        Map<String,List<Transaction>> legacyTransactions1 = new HashMap<>();
        // Task 1 : Add "EUR", "JPY" to the currency list.....
        legacyTransactions1.put("EUR", new ArrayList<Transaction>());
        // Java 7
        TransactionProcessor<Transaction> legacyProcessor = new TransactionProcessor<Transaction>() {
            @Override
            public void process(Transaction transaction) {
                System.out.println("Modern Transaction: " + transaction);
            };
        };

        // Java 9+ Diamond operator with anonymous inner class
        // The diamond operator (<>) is used to infer generic type parameters when instantiating generic classes, including anonymous inner classes.
        TransactionProcessor<Transaction> modernProcessor = new TransactionProcessor<>() {
            @Override
            public void process(Transaction transaction) {
                System.out.println("Modern Transaction: " + transaction);
            };
        };
        // Adding transaction to legacy Transactions
        legacyTransactions.get("USD").add(new Transaction("T1", BigDecimal.valueOf(100.50)));
        legacyTransactions.get("USD").add(new Transaction("T2", BigDecimal.valueOf(200.50)));

        // IC : try adding EUR, JPY
        System.out.println("Legacy Transactions: (USD)" + legacyTransactions) ;

        legacyTransactions1.get("EUR").add(new Transaction("T1", BigDecimal.valueOf(100.50)));
        legacyTransactions1.get("EUR").add(new Transaction("T2", BigDecimal.valueOf(200.50)));
        System.out.println("Legacy Transactions: (EUR)" + legacyTransactions1) ;

        // Task 2 : Add transactions to legacyProcessor
        System.out.println("Processing transaction using legacyProcessor:");
        for(Transaction t: legacyTransactions.get("USD")){
            legacyProcessor.process(t);
        }
        // Task 3 : Add transaction to modernProcessor
        System.out.println("Processing transaction using modernProcessor:");
        for(Transaction t: legacyTransactions1.get("EUR")){
            modernProcessor.process(t);
        }
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

class SecureTransaction extends Transaction{

    public SecureTransaction(String id, BigDecimal amount) {
        super(id, amount);
    }
}
//@FunctionalInterface
interface TransactionProcessor<T> {
    void process(T t);
}