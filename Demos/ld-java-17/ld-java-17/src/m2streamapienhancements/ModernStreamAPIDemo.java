package m2streamapienhancements;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ModernStreamAPIDemo {
    public static void main(String[] args) {
        //0. Sample Data initialization
        List<Transaction> transactions = Arrays.asList(
                new Transaction("T2", new BigDecimal(300.50)),
                new Transaction("T1", new BigDecimal(100.50)),
                new Transaction("T2", new BigDecimal(200.50)),
                new Transaction("T2", new BigDecimal(300.50))
        );

        // The takeWhile method is a new addition to the Stream API in Java 9. It allows you to take elements from a stream while a given predicate is true. Once the predicate returns false, the stream is terminated.
        //1. takeWhile :
        System.out.println("Scenario : Process the small transactions!");
        transactions
                .parallelStream()
                .takeWhile( t -> t.getAmount().compareTo(BigDecimal.valueOf(200)) < 0)
                .forEach(System.out::println);

        //2. dropWhile (Lab) use case skip smaller transactions.

        //3. ofNullable (Lab) : Handle nullable transaction (Java 9)
        Transaction validTransaction = new Transaction("T2", new BigDecimal(300.50));
        Stream.of(validTransaction).forEach(System.out::println);
        Transaction validTransaction1 = null;
        Stream.of(validTransaction1).forEach(System.out::println);
        Transaction nullableTransaction = retreivePendingTransaction();
        // Process the stream and handle the null gracefully.

        //6. teeing (Lab)  : Calculate average and total using TransactionSummary class.
        // Hint : using Collectiors and teeing method.
        // A paradigm shift from imperative approach to a stream approach

        // Imperative approach, what to do, a lot of how to do?
        double totalAmount = 0;
        for (Transaction transaction : transactions) {
            totalAmount += transaction.getAmount().doubleValue();
        }
        double averageAmount = totalAmount / transactions.size();
        System.out.println("Imperative Approach - Average: " + averageAmount + ", Total: " + totalAmount);

        // Imperative approach, focus more on what to do, a lot of how to do will be taken care by the Stream API?
        TransactionSummary summary = transactions.stream()
                .collect(
                        Collectors.teeing(
                                Collectors.averagingDouble(t -> t.getAmount().doubleValue()),
                                Collectors.summingDouble(t -> t.getAmount().doubleValue()),
                                        (avg, sum) -> new TransactionSummary(avg,sum)
                        )
                );

        System.out.println("\n  Transaction summary : "+summary);
        //Lab : use the toList method to collect the transactions into a list and try mutating it.

    }

    // rough work
    //     .takeWhile( t -> {
    //         try {
    //             t.getAmount().compareTo(BigDecimal.valueOf(200)) < 0
    //         } catch (NFE nfe) {
    //             return null / 0;
    //         }
    //     })

  private static Transaction retreivePendingTransaction(){
        return null;
  }
}
