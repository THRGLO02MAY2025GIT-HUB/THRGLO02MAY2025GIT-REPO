package f1diamond;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

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

    @Override
    public String toString() {
        return "Payment{" +
                "id='" + id + '\'' +
                ", amount=" + amount +
                ", timestamp=" + timestamp +
                '}';
    }
}

public class DiamondOperatorWithStreamsDemo {
    public static void main(String[] args) {
        List<Payment> legacyPayments = new ArrayList<>();
        legacyPayments.add(new Payment("P10", BigDecimal.valueOf(50), LocalDateTime.now().minusDays(2)));

        Comparator<Payment> legacyComparator = new Comparator<Payment>() {
            @Override
            public int compare(Payment p1, Payment p2) {
                return p1.getAmount().compareTo(p2.getAmount());
            }
        };

        List<Payment> moderatePayments = new ArrayList<>();
        Comparator<Payment> moderateComparator = new Comparator<>() {
            @Override
            public int compare(Payment p1, Payment p2) {
                return p1.getAmount().compareTo(p2.getAmount());
            }
        };

        legacyPayments.add(new Payment("P11", BigDecimal.valueOf(100), LocalDateTime.now()));
        legacyPayments.add(new Payment("P21", BigDecimal.valueOf(200), LocalDateTime.now().minusDays(1)));
        legacyPayments.add(new Payment("P31", BigDecimal.valueOf(150), LocalDateTime.now().minusDays(2)));

        Map<String, List<Payment>> groupedLegacyPayments = new TreeMap();
        for (Payment payment : legacyPayments) {
            String dayOfWeek = payment.getTimestamp().getDayOfWeek().toString();
            if(!groupedLegacyPayments.containsKey(dayOfWeek)) {
                groupedLegacyPayments.put(dayOfWeek, new ArrayList<>());
            }
            groupedLegacyPayments.get(dayOfWeek).add(payment);
        }

        legacyPayments.sort(legacyComparator);
        System.out.println(legacyPayments);

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

