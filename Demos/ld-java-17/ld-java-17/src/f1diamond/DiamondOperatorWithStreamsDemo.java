package f1diamond;
import java.util.function.Function;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Payment {
    private String id;
    private BigDecimal amount;
    private LocalDateTime timestamp;

    public Payment(String id, BigDecimal amount, LocalDateTime timestamp) {
        this.id = id;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    //    @Override
//    public String toString() {
//        return "Payment{" +
//                "id='" + id + '\'' +
//                ", amount=" + amount +
//                ", timestamp=" + timestamp +
//                '}';
//    }
    @Override
    public String toString() {
        return String.format("Payment{id='%s', amount=%s, date=%s}",
                id, amount, timestamp.truncatedTo(ChronoUnit.MINUTES));
    }
}

public class DiamondOperatorWithStreamsDemo {
    public static void main(String[] args) {
        List<Payment> legacyPayments = new ArrayList<>();
        List<Payment> modernPayments = new ArrayList<>();
        legacyPayments.add(new Payment("P10", BigDecimal.valueOf(50), LocalDateTime.now().minusDays(2)));
        Comparator<Payment> legacyComparator = new Comparator<Payment>() {
            @Override
            public int compare(Payment p1, Payment p2) {
                return p1.getAmount().compareTo(p2.getAmount());
            }
        };

        Comparator<Payment> moderateComparator = new Comparator<>() {
            @Override
            public int compare(Payment p1, Payment p2) {
                return p1.getAmount().compareTo(p2.getAmount());
            }
        };

        Comparator<Payment> modernComparator = new Comparator<>() {
            @Override
            public int compare(Payment p1, Payment p2) {
                return p1.getAmount().compareTo(p2.getAmount());
            }
        };

        legacyPayments.add(new Payment("P11", BigDecimal.valueOf(100), LocalDateTime.now()));
        legacyPayments.add(new Payment("P21", BigDecimal.valueOf(200), LocalDateTime.now().minusDays(1)));
        legacyPayments.add(new Payment("P31", BigDecimal.valueOf(150), LocalDateTime.now().minusDays(2)));


        modernPayments.add(new Payment("P1", BigDecimal.valueOf(100), LocalDateTime.now()));
        modernPayments.add(new Payment("P2", BigDecimal.valueOf(200), LocalDateTime.now().minusDays(1)));
        modernPayments.add(new Payment("P3", BigDecimal.valueOf(300), LocalDateTime.now().minusDays(2)));
        modernPayments.add(new Payment("P22", BigDecimal.valueOf(500), LocalDateTime.now().minusDays(1)));

        Map<String, List<Payment>> groupedLegacyPayments = new TreeMap();
        for (Payment payment : legacyPayments) {
            String dayOfWeek = payment.getTimestamp().getDayOfWeek().toString();
            if (!groupedLegacyPayments.containsKey(dayOfWeek)) {
                groupedLegacyPayments.put(dayOfWeek, new ArrayList<>());
            }
            groupedLegacyPayments.get(dayOfWeek).add(payment);
        }
//
        legacyPayments.sort(legacyComparator);
        System.out.println("Legacy Payments : " + legacyPayments);
        System.out.println("Grouped Legacy Payments : " + groupedLegacyPayments);

        // Print results with formatting
        System.out.println("\n=== Legacy Payments Grouped by Day ===");
        groupedLegacyPayments.forEach((day, payments) -> {
            System.out.printf("%s:%n", day);
            payments.forEach(p -> System.out.printf("  - %s: $%s%n", p.getId(), p.getAmount()));
        });

        // Stream grouping the modern way with sleek code (Diamond operator)
        Stream<Payment> paymentsStream = modernPayments.stream();
       Map<String, List<Payment>> groupedModernPayments =  paymentsStream.collect(Collectors.groupingBy(
                p -> p.getTimestamp().getDayOfWeek().toString(),
                () -> new TreeMap<>(), // Dimaond operator in the constructor
                Collectors.toCollection(ArrayList::new) // New TreeMap using method reference  instead of a lambda.....
        ));

        System.out.println("Modern Grouped Payments : " + groupedModernPayments);

        // Lab Activity 1 : Refactor the above code to display the grouped results with day in a formatted manner.
        // Lab Activity 2 : Apply streams and collectors to perform the task
//        Hint : Make a collection of modernPayments
//        Use the following methods :
//        stream(), collect, Collectors.groupingBy.......
//        Perform the sorting using th modernComparator

        // Result : Formatted
        // Legacy Payments grouped by Day, Sorted by amount
        // Modern Payments grouped by Day, sorted by amount.
    }
}

