/**
 * This code demonstrates the use of functional programming and lambda expressions in Java,
 * specifically focusing on the `groupingBy` collector from the Stream API. It highlights:
 * 
 * 1. The use of a lambda expression to extract the day of the week from a `Payment` object.
 * 2. The application of a `Supplier` to create a `TreeMap` for ordered grouping.
 * 3. The use of a `Collector` to collect elements into an `ArrayList`.
 * 
 * Key insights for learning:
 * - Understand how functional interfaces like `Function` can be implemented using lambdas.
 * - Learn how to customize grouping behavior with `groupingBy` by providing a key extractor, 
 *   a map factory, and a downstream collector.
 * - Explore the flexibility of combining functional programming constructs to achieve concise 
 *   and readable code.
 */
```java
.groupingBy(
                p -> p.getTimestamp().getDayOfWeek().toString(),
                () -> new TreeMap<>(), // Dimaond operator in the constructor
                Collectors.toCollection(ArrayList::new) // New TreeMap using method reference  instead of a lambda.....
        ));


        groupingBy(Function,Supplier,Collector);

        groupingBy(new Function() {
            @Override
            public Payment apply(Payment p) {
               return p.getTimestamp().getDayOfWeek().toString()
            },second parameter is a supplier, third parameter is a collector)

        groupingBy(p -> , p.getTimestamp().getDayOfWeek().toString(), second parameter is a supplier, third parameter is a collector)

        p -> p.getTimestamp().getDayOfWeek().toString()
          public abstract  R apply(T t);

         // 1. Create a functional interface that takes a Payment object and returns a String representing the day of the week.
         // 2. Use the functional interface to extract the day of the week from the Payment object. 
         Function getDayOfWeeek = new Function() {
            @Override
            public Payment apply(Payment p) {
               return p.getTimestamp().getDayOfWeek().toString()
            }


```