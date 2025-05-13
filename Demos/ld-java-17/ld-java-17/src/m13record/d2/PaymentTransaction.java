package m13record.d2;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Currency;

record PaymentTransaction(
        String id,
        BigDecimal amount,
        Currency currency,
        Instant timestamp,
        PaymentStatus status
) {
    private static final BigDecimal MAX_AMOUNT = BigDecimal.valueOf(1000000.00);

    // Regular canonical constructor
    public PaymentTransaction(String id, BigDecimal amount, Currency currency, Instant timestamp, PaymentStatus status) {
        this.id = id;
        this.amount = amount;
        this.currency = currency;
        this.timestamp = timestamp;
        this.status = status;
    }

    // Lab : Refactor code to use the compact canonical constructor
    // Compact canonical constructor
//    public PaymentTransaction {
//        validateAmount(amount);
//    }
    // Private validation method
    private static void validateAmount(BigDecimal amount) {
        System.out.println("Inside Validation!");
        if (amount.compareTo(BigDecimal.ZERO) < 0 || amount.compareTo(MAX_AMOUNT) > 0) {
            throw new IllegalArgumentException("Amount must be greater than or equal to zero");
        }
    }

    // Lab : Override the toString method
    // Lab : try overriding the getters

    public static void main(String[] args) {
        // Lab : Utilizie this constructor
//               PaymentTransaction tx2 = new PaymentTransaction(
//                "TX001",
//                new BigDecimal(500),
//                Currency.getInstance("USD"),
//        );

      // Create transactions using different constructors
        PaymentTransaction tx2 = new PaymentTransaction(
                "TX001",
                new BigDecimal(500),
                Currency.getInstance("USD"),
                Instant.now(),
                PaymentStatus.NEW
        );

        System.out.println("Transaction Id : " + tx2.id());
        System.out.println("Amount : " + tx2.amount() + " " + tx2.currency());
        System.out.println("Status : " + tx2.status());

        // Lab : Create the PaymentTransaction with validation, in case it fails, handle the exception gracefully
    }
}

enum PaymentStatus {NEW, PENDING, COMPLETED, FAILED}