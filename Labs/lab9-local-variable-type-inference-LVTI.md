# Lab: Local Variable Type Inference (LVTI) Best Practices in Java  
30 Minutes  

In this lab, you will explore the use of Local Variable Type Inference (`var`) in Java. You will analyze a fintech codebase related to loan management, refactor code to use `var` appropriately, and identify scenarios where explicit types are preferred. The goal is to follow best practices for readability and maintainability.

## Objective

- Understand when to use `var` for local variables in Java.
- Refactor code to use `var` only where type inference is clear and beneficial.
- Avoid using `var` in situations where it reduces code clarity.
- Apply best practices for exception handling and resource management with `var`.

---

## Code Overview

The program simulates a loan management system with classes for `Loan`, `Customer`, and `LoanService`. The `LoanService` class demonstrates various uses of `var` for local variables, collections, and try-with-resources.

### Key Features

1. **Appropriate Use of `var`**:  
    - Use `var` for local variables where the type is obvious from the right-hand side.
    - Avoid `var` for field declarations and when the type is not clear.

2. **Collections and Generics**:  
    - Use `var` with collections only when the generic type is clear from the assignment.

3. **Try-with-Resources**:  
    - Use `var` for resources when the type is evident.

4. **Exception Handling**:  
    - Do not use `var` for exception variables in catch blocks.

5. **Explicit Types for Clarity**:  
    - Use explicit types when the type cannot be easily inferred or when it improves readability.

---

## Lab Instructions

### Step 1: Analyze and Refactor Field Declarations

1. Identify and correct any use of `var` in field declarations.  
2. Ensure all fields use explicit types.

---

### Step 2: Refactor Local Variables with `var`

1. Use `var` for local variables where the type is clear from the assignment (e.g., `var applicationDate = LocalDate.now();`).  
2. Avoid `var` where the type is not obvious (e.g., method calls with unclear return types).

---

### Step 3: Collections and Generics

1. Use `var` for collections only when the generic type is clear from the right-hand side.  
2. Refactor any ambiguous uses of `var` in collections to use explicit types.

---

### Step 4: Try-with-Resources

1. Use `var` for resources in try-with-resources blocks only when the type is clear.  
2. Refactor any unclear uses to explicit types.

---

### Step 5: Exception Handling

1. Do not use `var` for exception variables in catch blocks.  
2. Refactor any such usage to use explicit exception types.

---

## Deliverables

1. A refactored `LoanService` class following LVTI best practices.
2. Correct use of `var` for local variables and resources.
3. Explicit types for fields, ambiguous local variables, and exception variables.
4. Comments explaining why `var` is or isn't used in each case.

---

## Code Template to Start With

```java
package fintech.loan;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Loan {
    private BigDecimal principal;
    private LocalDate startDate;
    private int termMonths;

    public Loan(BigDecimal principal, LocalDate startDate, int termMonths) {
        this.principal = principal;
        this.startDate = startDate;
        this.termMonths = termMonths;
    }

    public BigDecimal getPrincipal() {
        return principal;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public int getTermMonths() {
        return termMonths;
    }
}

class Customer {
    private String name;
    private String email;
    private List<Loan> loans;

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
        this.loans = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void addLoan(Loan loan) {
        loans.add(loan);
    }
}

class DatabaseUtil {
    public static Connection getConnection() {
        return null;
    }
}

class LoanService {
    private BigDecimal totalOutstanding;
    private List<Loan> allLoans;
    private List<Customer> allCustomers;

    public LoanService() {
        this.totalOutstanding = BigDecimal.ZERO;
        this.allLoans = new ArrayList<>();
        this.allCustomers = new ArrayList<>();
    }

    public void processLoan(BigDecimal amount, Customer customer) {
        LocalDate applicationDate = LocalDate.now();
        ArrayList<Loan> loans = new ArrayList<Loan>();
        BigDecimal calculatedInterest = calculateInterest(amount);
        Loan loan = new Loan(amount, applicationDate, 12);
        loans.add(loan);
        customer.addLoan(loan);
        String message = String.format("Loan of %s granted to %s", amount, customer.getName());
        System.out.println(message);
        try (Connection connection = DatabaseUtil.getConnection()) {
            Object loanData = connection != null
                ? connection.createStatement().executeQuery("SELECT principal FROM Loan").getObject(1)
                : null;
            List<Customer> customers = List.of(new Customer("Alice", "alice@email.com"));
            List<String> names = customers.stream()
                .map(c -> c.getName())
                .toList();
        } catch (SQLException ex) {
            System.out.println("SQL Error: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error Processing Loan: " + ex.getMessage());
        }
    }

    private BigDecimal calculateInterest(BigDecimal amount) {
        return amount.multiply(BigDecimal.valueOf(0.05));
    }

    public void addLoan(Loan loan) {
        allLoans.add(loan);
        totalOutstanding = totalOutstanding.add(loan.getPrincipal());
    }

    public void addCustomer(Customer customer) {
        allCustomers.add(customer);
    }

    public List<Loan> getAllLoans() {
        return allLoans;
    }

    public List<Customer> getAllCustomers() {
        return allCustomers;
    }

    public void printLargeLoans(BigDecimal threshold) {
        ArrayList<Loan> largeLoans = new ArrayList<Loan>();
        for (Loan loan : allLoans) {
            if (loan.getPrincipal().compareTo(threshold) > 0) {
                largeLoans.add(loan);
            }
        }
        for (Loan l : largeLoans) {
            System.out.println("Large loan: " + l.getPrincipal());
        }
    }
}
```


## Expected Output

- Code compiles and runs without errors.
- All uses of `var` follow best practices.
- Comments in code explain the rationale for using or not using `var`.

---
