# Lab 8: Exploring Java Records and Advanced Features
30 Minutes

In this lab, you will explore Java Records, a feature introduced in Java 14, and learn how to use them effectively. You will refactor code to use compact canonical constructors, perform constructor overloading, override methods, and handle exceptions gracefully.

## Objective

- Understand the concept of Java Records and their use cases.
- Refactor code to use compact canonical constructors.
- Implement constructor overloading in Records.
- Override methods like `toString` and accessor methods.
- Handle exceptions during object creation.

---

## Code Overview

The `PaymentTransaction` record represents a payment transaction with fields such as `id`, `amount`, `currency`, `timestamp`, and `status`. The record includes validation logic, a static constant for the maximum amount, and placeholders for additional features.

### Key Features

1. **Compact Canonical Constructor**:
    - Refactor the regular canonical constructor to a compact form.
    - Perform validation within the compact constructor.

2. **Constructor Overloading**:
    - Add an overloaded constructor that accepts fewer parameters and initializes the remaining fields with default values.

3. **Method Overrides**:
    - Override the `toString` method to customize the string representation of the record.
    - Optionally override accessor methods to add custom behavior.

4. **Exception Handling**:
    - Handle exceptions gracefully when validation fails during object creation.

---

## Lab Instructions

### Step 1: Refactor to Use Compact Canonical Constructor

1. Use compact canonical constructor.
2. Perform validation for the `amount` field within the constructor.
3. Use the `validateAmount` method to ensure the amount is within the valid range.

---

### Step 2: Implement Constructor Overloading

1. Add an overloaded constructor that accepts only `id`, `amount`, and `currency`.
2. Set default values for `timestamp` (e.g., `Instant.now()`) and `status` (e.g., `PaymentStatus.NEW`).

---

### Step 3: Override the `toString` Method

1. Customize the `toString` method to prepend "PMT" to the transaction ID.
    - For example, if the transaction ID is `TX001`, the output should be `PMTTX001`.
2. Ensure the overridden method includes all relevant fields in the string representation.

---

### Step 4: Handle Exceptions Gracefully

1. Create a `PaymentTransaction` instance with an invalid amount (e.g., negative or exceeding the maximum limit).
2. Catch the exception and display an appropriate error message.

---

### Step 5: Test the Record

1. Create multiple `PaymentTransaction` instances using different constructors.
2. Print the details of each transaction using the overridden `toString` method.
3. Verify that the validation logic works as expected.

---

## Deliverables

1. A refactored `PaymentTransaction` record with a compact canonical constructor.
2. An overloaded constructor with default values for some fields.
3. An overridden `toString` method with a customized format.
4. A main method demonstrating the creation and validation of `PaymentTransaction` instances.

---

## Code Template to Start With

```java
package m13record.d2;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Currency;

record PaymentTransaction(
        String id,
        BigDecimal amount,
        Currency currency,
        Instant timestamp,
        PaymentStatus status
) {
    private static final BigDecimal MAX_AMOUNT = BigDecimal.valueOf(1000000.00);

    // Regular canonical constructor
    public PaymentTransaction(String id, BigDecimal amount, Currency currency, Instant timestamp, PaymentStatus status) {
        this.id = id;
        this.amount = amount;
        this.currency = currency;
        this.timestamp = timestamp;
        this.status = status;
    }

    // Lab : Refactor code to use the compact canonical constructor
    // Use Compact canonical constructor
//    public PaymentTransaction {
//        validateAmount(amount);
//    }

    // Lab : Perform constructor overloading
//    public PaymentTransaction(String id, BigDecimal amount, Currency currency) {
    // required code snippet here.
//    }

    // Private validation method
    private static void validateAmount(BigDecimal amount) {
        System.out.println("Inside Validation!");
        if (amount.compareTo(BigDecimal.ZERO) < 0 || amount.compareTo(MAX_AMOUNT) > 0) {
            throw new IllegalArgumentException("Amount must be greater than or equal to zero");
        }
    }

    // Lab : Override the toString method, add (PMT before the transaction ID)
    // if transactionID is "TX001" the expected result is PMTTXN001.
    // Lab : try overriding the getters

    public static void main(String[] args) {
        // Lab : Utilizie this constructor
//               PaymentTransaction tx2 = new PaymentTransaction(
//                "TX001",
//                new BigDecimal(500),
//                Currency.getInstance("USD"),
//        );

        // Create transactions using different constructors
        PaymentTransaction tx2 = new PaymentTransaction(
                "TX001",
                new BigDecimal(500),
                Currency.getInstance("USD"),
                Instant.now(),
                PaymentStatus.NEW
        );

        System.out.println("Transaction Id : " + tx2.id());
        System.out.println("Amount : " + tx2.amount() + " " + tx2.currency());
        System.out.println("Status : " + tx2.status());

        // Lab : Create the PaymentTransaction with validation, in case it fails, handle the exception gracefully
    }
}

enum PaymentStatus {NEW, PENDING, COMPLETED, FAILED}
```

Expected Output:
```
PMTTX001 [amount=500, currency=USD, timestamp=2023-05-02T10:15:30Z, status=NEW]
Error creating transaction: Amount must be between 0 and 1000000.00
```

---
