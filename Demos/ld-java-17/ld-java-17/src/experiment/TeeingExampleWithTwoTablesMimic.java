package experiment;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TeeingExampleWithTwoTablesMimic {
    public static void main(String[] args) {
        // Simulated tables from the database
        List<Integer> table1 = List.of(10,20,30,40,50);
        List<Integer> table2 = List.of(10,20,30,40,50);

        //Combine the tables into a single stream
        Stream<Integer> combinedStream = Stream.concat(table1.stream(), table2.stream());

        // Using teeing
        Map<String, Double> result = combinedStream.collect(
                Collectors.teeing(
                        Collectors.summingDouble( i -> i),
                        Collectors.averagingDouble(i -> i),
                        (sum,avg) -> Map.of("Sum", sum, "Average", avg)
                )
        );
        System.out.println("Combined Results: " + result);
        System.out.println(result.get("Sum"));
        System.out.println(result.get("Average"));
    }
    // The "Thryve" is called as a string literal in the code.
    String company = "Thryve";
}

// Productivity packed. Productivity packed. Custom Aggregation. Streamlining code.