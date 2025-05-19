:books: **THE MASTER GUIDE TO JAVA 17+**  
                

:beginner: _**Diamond Operator for Anonymous Inner Classes**_  
## Introduction

The diamond operator (`<>`) was introduced in Java 7 to simplify the use of generics. This document compares legacy and modern approaches in Java, demonstrating the benefits of using anonymous inner classes with the diamond operator.

## Legacy Approach (Pre-Java 7)

```java
// Verbose generic type declarations
Map<String, List<Transaction>> transactions = 
    new HashMap<String, List<Transaction>>();

// Explicit type parameters in anonymous inner class
Comparator<Transaction> comparator = new Comparator<Transaction>() {
    @Override
    public int compare(Transaction t1, Transaction t2) {
        return t1.getAmount().compareTo(t2.getAmount());
    }
};

// Complex collection initialization
Map<String, Set<Payment>> payments = 
    new HashMap<String, Set<Payment>>() {{
        put("USD", new HashSet<Payment>());
        put("EUR", new TreeSet<Payment>());
    }};
```

## Modern Approach (Java 7+ and Java 9+)

```java
// Simplified generic declarations
Map<String, List<Transaction>> transactions = new HashMap<>();

// Diamond operator with anonymous inner class (Java 9+)
Comparator<Transaction> comparator = new Comparator<>() {
    @Override
    public int compare(Transaction t1, Transaction t2) {
        return t1.getAmount().compareTo(t2.getAmount());
    }
};

// Streamlined collection initialization
Map<String, Set<Payment>> payments = new HashMap<>() {{
    put("USD", new HashSet<>());
    put("EUR", new TreeSet<>());
}};

// Modern Stream API usage
List<Transaction> filtered = transactions.values().stream()
    .flatMap(List::stream)
    .sorted(new Comparator<>() {
        @Override
        public int compare(Transaction t1, Transaction t2) {
            return t1.getAmount().compareTo(t2.getAmount());
        }
    })
    .collect(Collectors.toList());

```


:beginner: _**Stream API Improvements**_  

| Feature | Version | Description | Example |
|---------|---------|-------------|---------|
| `takeWhile()` | Java 9 | Takes elements from the stream while predicate is true | `Stream.of(1,2,3,4,5).takeWhile(x -> x < 3)` |
| `dropWhile()` | Java 9 | Drops elements from the stream while predicate is true | `Stream.of(1,2,3,4,5).dropWhile(x -> x < 3)` |
| `ofNullable()` | Java 9 | Creates a stream of 0 or 1 elements, handling null | `Stream.ofNullable(nullable)` |
| `iterate()` | Java 9 | Enhanced iterate with predicate to control iteration | `Stream.iterate(1, x -> x < 100, x -> x * 2)` |
| `teeing()` | Java 12 | Combines results of two collectors into a final result | `Stream.of(1,2,3).collect(teeing(summingInt(i->i), counting(), (sum,count) -> sum/count))` |
| `toList()` | Java 16 | Convenient way to collect stream elements to an unmodifiable List | `stream.toList()` |

``` java
// 1. takeWhile example - takes elements while predicate is true
List<Integer> takeWhileList = Stream.of(2, 4, 6, 8, 9, 10, 12)
    .takeWhile(n -> n % 2 == 0)
    .collect(Collectors.toList()); // Returns [2, 4, 6, 8]

// 2. dropWhile example - drops elements while predicate is true
List<Integer> dropWhileList = Stream.of(2, 4, 6, 7, 8, 9, 10)
    .dropWhile(n -> n % 2 == 0)
    .collect(Collectors.toList()); // Returns [7, 8, 9, 10]

// 3. ofNullable example - creates stream handling null
String str = null;
Stream<String> stream = Stream.ofNullable(str); // Creates empty stream

// 4. Enhanced iterate example - with predicate control
List<Integer> powers = Stream.iterate(1, 
    x -> x < 100,    // Predicate to stop at 100
    x -> x * 2)      // Next element generator
    .collect(Collectors.toList()); // Returns [1, 2, 4, 8, 16, 32, 64]

// 5. teeing example - combines two collectors
double average = Stream.of(1, 2, 3)
    .collect(Collectors.teeing(
        Collectors.summingInt(i -> i),
        Collectors.counting(),
        (sum, count) -> sum / (double) count
    )); // Returns 2.0

// 6. toList example - creates unmodifiable list
List<Integer> numbers = Stream.of(1, 2, 3, 4, 5)
    .toList(); // Returns unmodifiable List[1, 2, 3, 4, 5]

```

## Key Benefits
- More precise control over stream processing
- Better handling of null values
- Improved iteration capabilities
- Enhanced filtering options
- Simplified collection operations 
- Advanced stream combining operations 

## Usage Notes
- `takeWhile`: Stops when condition becomes false
- `dropWhile`: Starts including when condition becomes false
- `toList()`: Creates unmodifiable list (preferred over collect(Collectors.toList()))
- All operations are non-interfering and stateless
- Compatible with parallel streams


:beginner: _**Collection Factory Methods**_  
## Introduction
Collection factory methods were introduced in Java 9 to provide convenient ways to create small, immutable collections.

## Collection Factory Methods in Java 9+

The following factory methods were introduced to create immutable collections:

### List Factory Methods
```java
List.of()                    // Empty list
List.of(E e1)               // Single element
List.of(E e1, E e2)         // Two elements
List.of(E... elements)      // Variable number of elements
```

### Set Factory Methods
```java
Set.of()                    // Empty set
Set.of(E e1)               // Single element
Set.of(E e1, E e2)         // Two elements
Set.of(E... elements)      // Variable number of elements
```

### Map Factory Methods
```java
Map.of()                    // Empty map
Map.of(K k1, V v1)         // Single entry
Map.of(K k1, V v1, K k2, V v2)  // Multiple entries
Map.ofEntries(Entry<K,V>... entries)  // Entry-based creation
```
## Factory Methods Overview

### List Factory Methods
| Method | Java Version | Description | Example |
|--------|-------------|-------------|---------|
| `List.of()` | Java 9+ | Creates immutable list | `List<String> list = List.of("a", "b", "c");` |
| `List.of(E... elements)` | Java 9+ | Creates immutable list with varargs | `List<Integer> nums = List.of(1, 2, 3);` |

### Set Factory Methods
| Method | Java Version | Description | Example |
|--------|-------------|-------------|---------|
| `Set.of()` | Java 9+ | Creates immutable set | `Set<String> set = Set.of("x", "y", "z");` |
| `Set.of(E... elements)` | Java 9+ | Creates immutable set with varargs | `Set<Integer> nums = Set.of(1, 2, 3);` |

### Map Factory Methods
| Method | Java Version | Description | Example |
|--------|-------------|-------------|---------|
| `Map.of()` | Java 9+ | Creates empty immutable map | `Map<String, Integer> map = Map.of();` |
| `Map.of(K,V)` | Java 9+ | Creates single entry map | `Map<String, Integer> map = Map.of("one", 1);` |
| `Map.ofEntries(Entry<K,V>...)` | Java 9+ | Creates map from entries | `Map<String, Integer> map = Map.ofEntries(Map.entry("one", 1));` |

## Key Characteristics
- All collections created are immutable
- Null elements are not allowed
- Duplicate elements are not allowed in Sets
- For Maps, duplicate keys are not allowed


:beginner: _**Private Methods in Interfaces**_  

## Introduction
Java 9 introduced private methods in interfaces, enhancing code reusability and encapsulation within interface definitions. This feature is particularly valuable in financial technology applications where complex business logic often requires modular, secure implementations.

## Key Characteristics
- Private methods can only be used within the interface
- Can be static or instance methods
- Support code reuse between default methods
- Cannot be abstract
- Must have an implementation

## Financial Domain Implementation Example

### Transaction Processing Interface
```java
public interface TransactionProcessor {
    void execute(Transaction tx);
    
    default void processWithAudit(Transaction tx) {
        String auditId = generateAuditId();
        validateTransaction(tx);
        logTransaction(tx, auditId);
        execute(tx);
    }
    
    private void validateTransaction(Transaction tx) {
        if (tx == null) throw new IllegalArgumentException("Transaction cannot be null");
        validateAmount(tx.getAmount());
        validateCurrency(tx.getCurrency());
    }
    
    private void validateAmount(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Invalid amount");
        }
    }
    
    private void validateCurrency(String currency) {
        if (!getSupportedCurrencies().contains(currency)) {
            throw new UnsupportedOperationException("Unsupported currency: " + currency);
        }
    }
    
    private static Set<String> getSupportedCurrencies() {
        return Set.of("USD", "EUR", "GBP", "JPY");
    }
    
    private static String generateAuditId() {
        return "AUDIT-" + UUID.randomUUID().toString();
    }
    
    private void logTransaction(Transaction tx, String auditId) {
        // Private helper for logging
        System.out.printf("Processing transaction [%s]: %s%n", auditId, tx);
    }
}
```

:beginner: _**Records**_  

Modern Java syntax for immutable data transfer objects  
Records provide a concise way to create classes that hold data  
Useful for financial transactions and data models  


## Before vs After Records: Implementation Comparison

| Aspect            | Pre-Records (Traditional Class)                            | With Records                           | Benefits                        |
|-------------------|----------------------------------------------------------|---------------------------------------|--------------------------------|
| Class Declaration | `public class Transaction { private final String id; ... }` | `public record Transaction(String id)` | Reduced boilerplate, cleaner syntax |
| Immutability      | Manual final fields + private setters                     | Automatic immutability                 | Better thread safety, less error-prone |
| Constructor       | Manual implementation required                            | Auto-generated canonical constructor   | Less code maintenance          |
| Getters          | `public String getId() { return id; }`                    | Auto-generated `id()` method          | Concise accessor methods       |
| equals/hashCode   | Manual implementation needed                              | Auto-generated                        | Consistent object comparison   |
| toString          | Custom implementation required                            | Auto-generated readable format        | Better debugging experience    |
| Encapsulation    | Manual private fields + getters                           | Automatic component encapsulation     | Safer data handling           |
