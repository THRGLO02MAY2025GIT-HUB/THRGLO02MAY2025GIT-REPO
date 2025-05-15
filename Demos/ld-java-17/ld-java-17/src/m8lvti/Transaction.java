package m8lvti;

import java.math.BigDecimal;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Transaction {
    private BigDecimal amount;

    public Transaction(BigDecimal amount) {
        this.amount = amount;
    }
}


class Payment {
    private BigDecimal amount;

    public Payment(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}

class DatabaseConnection {
    public static Connection getConnection() {
        return null; // Simplified for convenience
    }
}

class PaymentProcessor {
    public static void main(String[] args) {

    }

    // Don't use var for field declarations
    private BigDecimal balance; // NOT:   private var balance;


    public void process(BigDecimal amount) { // NOT : public void process (var amount)
        //Good : Clear type inference
        var timestamp = LocalDateTime.now();

        //Good : Collection with types specified on generics
        var transactions = new ArrayList<Transaction>();

        // Bad : Unclear type from method return
        var result = complexCalculation(); // Avoid when type is not obvious

        //Good : Try-with-resources
        try (var connection = DatabaseConnection.getConnection()) {
            // Bad : Chained operations making type unclear
//        var processed = connection.createStatement()
//                .executeQuery("Select amount from Transaction")
//                .getObject(1);

            Object processed = connection.createStatement()
                    .executeQuery("Select amount from Transaction")
                    .getObject(1);

            // Good : Stream with clear types
            var payments = List.of(new Payment(new BigDecimal("100")));
            var total = payments.stream()
                    .map(Payment::getAmount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        } catch (Exception exception) {
            // Bad : Don't use var for exception handling
//            var exception1 = exception;
            Exception exception1 = exception;
            System.out.println("Error Processing Payment : " + exception1.getMessage());
        }
    }

    private BigDecimal complexCalculation() {
        return BigDecimal.ONE;
    }

    // IP : Analyze the existing code base we have developed together and experiment on where
    // LVTI can be used without compilation error
    // Following Good Practices
}