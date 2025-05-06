## Decode the method for a thorough understanding 0f functional programming in Java 8 and beyond.

1. Explore Function
2. Explore Supplier
3. Explore Collector

```java

``` java
.groupingBy(
                p -> p.getTimestamp().getDayOfWeek().toString(),
                () -> new TreeMap<>(), // Dimaond operator in the constructor
                Collectors.toCollection(ArrayList::new) // New TreeMap using method reference  instead of a lambda.....
        ));

        ```