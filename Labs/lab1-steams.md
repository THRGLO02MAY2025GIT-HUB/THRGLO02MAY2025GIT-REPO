# Lab 1: Refactoring Code to Apply Stream API and Date Time API [30 minutes]

## Lab Activities

1. **Lab Activity 1A**: Refactor the code to display the grouped results with the day in a formatted manner.
2. **Lab Activity 2B**: Apply streams and collectors to perform the task.

### Hints:
- Make a collection of `modernPayments`.
- Use the following methods:
    - `stream()`
    - `collect`
    - `Collectors.groupingBy`
- Perform sorting using the `modernComparator`.

### Expected Result:
- **Formatted Output**:
    - Legacy Payments grouped by day, sorted by amount.
    - Modern Payments grouped by day, sorted by amount.

---

### Code Example

```java
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

                Map<String, List<Payment>> groupedLegacyPayments = new TreeMap<>();
                for (Payment payment : legacyPayments) {
                        String dayOfWeek = payment.getTimestamp().getDayOfWeek().toString();
                        groupedLegacyPayments.computeIfAbsent(dayOfWeek, k -> new ArrayList<>()).add(payment);
                }

                legacyPayments.sort(legacyComparator);
                System.out.println(legacyPayments);
           
                // Placeholder for the Lab Activity Code

                // Lab Activity 1A: Refactor the above code to display the grouped results with day in a formatted manner.
                // Lab Activity 1B: Apply streams and collectors to perform the task.
                // Hint: Make a collection of modernPayments.
                // Use the following methods:
                // stream(), collect, Collectors.groupingBy...
                // Perform the sorting using the modernComparator.

                // Result: Formatted
                // Legacy Payments grouped by Day, Sorted by amount.
                // Modern Payments grouped by Day, sorted by amount.
        }
}
```