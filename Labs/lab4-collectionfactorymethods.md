
### Lab Activity 1: Exploring Collection Factory Methods in Java

#### Duration : 30 minutes
In this lab, you will explore the immutability, null handling, efficiency, uniqueness, and functionality of collections created using factory methods in Java. Follow the scenarios below and write the corresponding code in the in the main method of the partial code snippet.

## Scenarios

### 1. Demonstrating Immutability
Write code to demonstrate that collections created using factory methods are immutable. Attempt to add a new element to the `TRANSACTION_STATUSES` list and handle the exception.

### 2. Demonstrating Null Handling
Write code to demonstrate that factory methods do not accept `null` elements. Attempt to create a list containing a `null` value and handle the exception.

### 3. Demonstrating Efficient Empty Collections
Write code to demonstrate that empty collections created using factory methods are efficient and return the same instance.

### 4. Demonstrating Set Uniqueness
Write code to demonstrate that sets created using factory methods cannot contain duplicate elements. Attempt to create a set with duplicate values and handle the exception.

### 5. Demonstrating Map Functionality
Write code to iterate over the `CURRENCY_EXCHANGE_RATES` map and print each key-value pair.



## Partial Code Snippet

```java
package m3collectionfactorymethods;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionFactoryMethodsDemo {
    // A list of transaction statuses would be PENDING, COMPLETED, FAILED, etc.
    // A set of Payment methods would be CREDIT_CARD, DEBIT_CARD, PAYPAL, etc.
    // A default method of Payment would be CASH.
    // A map of Currency Exchange rates would be USD to INR, EUR to INR, etc.

    // Static factory methods
    private static final List<String> EMPTY_STATUSES = List.of();
    private static final List<String> TRANSACTION_STATUSES = List.of("PENDING", "COMPLETED", "FAILED", "ABORTED");
    private static final Set<String> PAYMENT_METHODS = Set.of("CREDIT_CARD", "DEBIT_CARD", "BANK_TRANSFER", "CRYPTO", "PAYPAL");
    private static final Set<String> DEFAULT_METHOD = Set.of("CASH");

    private static final Map<String, Double> CURRENCY_EXCHANGE_RATES = Map.of(
            "USD_EUR", 0.9,
            "USD_GBP", 0.7,
            "USD_JPY", 150.5,
            "USD_INR", 75.0
    );
}

```

### Lab Activity 2: Explore your existing code-base in your current project at your organization and find areas where you can apply these immutable collections and collection factory methods. 


