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

:beginner: _**New Stack-Walking API**_  

:beginner: _**Compact Strings**_  

## What are Compact Strings?
Compact Strings optimize the internal representation of String objects by using a byte array instead of a char array when possible. This feature is especially beneficial in FinTech applications where large volumes of ASCII-based financial data are processed.

## Benefits for FinTech Applications
- Reduced memory footprint for string-heavy operations
- Improved performance in financial data processing
- More efficient handling of transaction records
- Better resource utilization in high-frequency trading systems

## Technical Implementation
```java
// Before Java 9
String transaction = new String("TXN123"); // Uses 2 bytes per character

// After Java 9
String transaction = "TXN123"; // Uses 1 byte per character when possible
```

## Feature Comparison Table

| Aspect              | Traditional Strings          | Compact Strings                    |
|---------------------|-----------------------------|------------------------------------|
| Memory Usage        | 2 bytes per character       | 1 byte for Latin-1, 2 bytes for others |
| Performance Impact  | Baseline                    | Up to 15% memory savings           |
| Character Encoding  | UTF-16                      | Latin-1 or UTF-16                  |
| Best Use Case       | Unicode-heavy text          | ASCII financial data               |

:beginner: _**Local-Variable Type Inference**_  

Local Variable Type Inference (LVTI) is a feature introduced in Java 10 that allows you to declare local variables without explicitly stating their type. Instead, the compiler infers the type based on the context.



## Key Points

- Uses the `var` keyword
- Only works for local variables
- The type is inferred at compile-time
- Requires initialization at declaration

## Example

```java
var message = "Hello World"; // infers String
var number = 42;            // infers int
var list = new ArrayList<String>(); // infers ArrayList<String>
```

## Best Practices

1. Use when the type is obvious from the initialization
2. Avoid in cases where type clarity is important
3. Don't use just to shorten long type names-+-+-+-+-+

## Do's and Don'ts

| Do ✅ | Don't ❌ |
|-------|----------|
| Use `var` when type is obvious | Use `var` with raw types |
| Use with well-named methods | Use in complex lambda expressions |
| Use for loop iterators | Use for method parameters |
| Use for short, local scopes | Use for class fields |
| Use with diamond operator | Use when type is unclear |


:beginner: _**Switch Expressions**_  

:beginner: _**Pattern Matching**_  

:beginner: _**Compact Number Formatting**_  

Compact Number Formatting is a feature in Java that allows formatting numbers in a human-readable format, particularly useful for displaying large numbers in financial applications.

## Basic Usage

```java
// NumberFormat.getCompactNumberInstance creates a formatter with specified locale and style
// Style.SHORT produces abbreviated formats (K for thousands, M for millions, B for billions)
// Style.LONG produces full word formats (thousand, million, billion)
import java.text.NumberFormat;
import java.util.Locale;

public class CompactNumberFormattingDemo {
    public static void main(String[] args) {
        System.out.println("Basic Compact Number Demo:");
        demoBasicFormatting();
        
        System.out.println("\nTransaction Formatting Demo:");
        demoTransactionFormatting();
        
        System.out.println("\nPrecision Formatting Demo:");
        demoPrecisionFormatting();
        
        System.out.println("\nInternational Formatting Demo:");
        demoInternationalFormatting();
    }

    private static void demoBasicFormatting() {
        NumberFormat fmt = NumberFormat.getCompactNumberInstance(
            Locale.US, NumberFormat.Style.SHORT);
        // K represents thousands (Kilo) - 1000 becomes 1K
        System.out.println(fmt.format(1000));        // 1K
        // M represents millions (Mega) - 1000000 becomes 1M
        System.out.println(fmt.format(1000000));     // 1M
        // B represents billions (Billion) - 1000000000 becomes 1B
        System.out.println(fmt.format(1000000000));  // 1B
    }

    private static void demoTransactionFormatting() {
        // SHORT style uses abbreviated symbols (K, M, B)
        NumberFormat shortFmt = NumberFormat.getCompactNumberInstance(
            Locale.US, NumberFormat.Style.SHORT);
        // LONG style spells out the scale (thousand, million, billion)
        NumberFormat longFmt = NumberFormat.getCompactNumberInstance(
            Locale.US, NumberFormat.Style.LONG);
        
        double amount = 1234567.89;
        // Formats to 1.2M - rounds to one decimal place by default
        System.out.println("Short: " + shortFmt.format(amount));
        // Formats to "1.2 million" - full word representation
        System.out.println("Long: " + longFmt.format(amount));
    }

    private static void demoPrecisionFormatting() {
        NumberFormat fmt = NumberFormat.getCompactNumberInstance(
            Locale.US, NumberFormat.Style.SHORT);
        // Controls number of decimal places in the formatted output
        fmt.setMaximumFractionDigits(2);
        
        // Shows 1.23K instead of 1.2K - more precise representation
        System.out.println(fmt.format(1234));
        // Shows 1.23M instead of 1.2M - maintaining 2 decimal places
        System.out.println(fmt.format(1234567));
    }

    private static void demoInternationalFormatting() {
        double amount = 1234567.89;
        // US format uses K, M, B notation
        NumberFormat usFmt = NumberFormat.getCompactNumberInstance(
            Locale.US, NumberFormat.Style.SHORT);
        // German format uses Tsd. (thousand) and Mio. (million)
        NumberFormat gerFmt = NumberFormat.getCompactNumberInstance(
            Locale.GERMANY, NumberFormat.Style.SHORT);
        // Japanese format uses 万 (10000) and 億 (100 million) as bases
        NumberFormat jpFmt = NumberFormat.getCompactNumberInstance(
            Locale.JAPAN, NumberFormat.Style.SHORT);
        
        // US uses period as decimal separator
        System.out.println("US: " + usFmt.format(amount));
        // German uses comma as decimal separator and Mio. for million
        System.out.println("DE: " + gerFmt.format(amount));
        // Japanese uses 万 (man) which represents 10000
        System.out.println("JP: " + jpFmt.format(amount));
    }
}
```

:beginner: _**Text Blocks**_  

A text block is a multi-line string literal that avoids the need for most escape sequences, automatically formats the string in a predictable way, and gives the developer control over the format when desired.

## Questions and Answers About Text Blocks

1. **Q: What are text blocks?**
    ```java
    String example = """
         Text blocks are multi-line
         string literals with automatic
         formatting support
         """;
    ```

2. **Q: How do you start a text block?**
    ```java
    String block = """
         Start with three double quotes
         on a new line
         """;
    ```

3. **Q: Can text blocks contain quotes?**
    ```java
    String quotes = """
         "Yes, they can contain 'single'
         and "double" quotes freely"
         """;
    ```

4. **Q: How is indentation handled?**
    ```java
    String indent = """
              This line is indented
         This line isn't
              Back to indent
         """;
    ```

5. **Q: Can you use escape characters?**
    ```java
    String escaped = """
         Yes! Use \n for new lines
         \t for tabs, etc.
         """;
    ```
    6. **Q: Does it support Unicode characters?**
        ```java
        String unicode = """
             Yes! 💰 €uro ¥en 元
             International currency symbols
             are fully supported
             """;
        ```

    7. **Q: How are line terminators handled?**
        ```java
        String lines = """
             Each line ends with \n
             automatically. Explicit line
             breaks can be added\n\n
             """;
        ```

    8. **Q: Can text blocks be concatenated?**
        ```java
        String combined = """
             First block
             """ + """
             Second block
             """;
        ```

    9. **Q: Are there performance implications?**
        ```java
        String optimized = """
             Text blocks are optimized
             at compile time for
             better performance
             """;
        ```

    10. **Q: How is whitespace handled?**
        ```java
        String whitespace = """
             Leading spaces are based on
                the closing delimiter position.
             Trailing spaces are preserved  
             """;
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

:beginner: _**Sealed Classes**_  

:beginner: _**Modular System (Project Jigsaw) && Strong encapsulation of JDK internals**_  