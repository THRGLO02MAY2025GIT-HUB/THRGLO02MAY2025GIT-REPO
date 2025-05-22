# Lab: Identifying SRP Violation in a Healthcare Service

30 Minutes

In this lab, you will analyze a healthcare domain example that violates the Single Responsibility Principle (SRP). You will see how combining multiple concerns—such as patient registration, appointment scheduling, logging, and notifications—into a single class leads to code that is hard to maintain and extend. This exercise follows the same pattern as the FinTech SRP violation example.

---

## Objective

- Understand what constitutes an SRP violation in a healthcare context.
- Identify the drawbacks of combining multiple responsibilities in one class.
- Prepare for refactoring towards SRP compliance.

---

## Code Overview

The `HealthcareServiceSRPViolation` class below handles patient registration, appointment scheduling, logging, and notifications—all within a single method. This design violates SRP because each responsibility should be managed by a separate class.

---

## Code Example

```java
package com.demo.solid.srp.violation;

public class HealthcareServiceSRPViolation {

    public void processPatient(String action, String patientName, String details, String patientEmail) {
        // Patient registration or appointment scheduling
        if ("register".equalsIgnoreCase(action)) {
            System.out.println("Registering patient: " + patientName);
        } else if ("schedule".equalsIgnoreCase(action)) {
            System.out.println("Scheduling appointment for: " + patientName + " Details: " + details);
        }

        // Logging
        System.out.println("Logged action '" + action + "' for patient: " + patientName);

        // Notification
        System.out.println("Sending email to " + patientEmail + " regarding '" + action + "'");
    }
}
```

---

## Lab Instructions

1. **Review the Code:**  
   Examine the `HealthcareServiceSRPViolation` class and identify the different responsibilities it handles.

2. **Run and Observe:**  
   Write a simple main method to call `processPatient` with different actions and observe the output.

3. **Discuss Drawbacks:**  
   List the problems that may arise from this design (e.g., difficult to test, hard to extend, risk of bugs).

4. **Prepare for Refactoring:**  
   Suggest how you might split this class into SRP-compliant components.

---

## Deliverables

1. A list of responsibilities handled by the class.
2. Example output from running the code with different actions.
3. A brief summary of why this design is problematic.
4. Suggestions for refactoring towards SRP compliance.

---

### Example Usage

```java
public class Main {
    public static void main(String[] args) {
        HealthcareServiceSRPViolation service = new HealthcareServiceSRPViolation();
        service.processPatient("register", "Alice", "", "alice@example.com");
        service.processPatient("schedule", "Alice", "2024-07-01 10:00AM", "alice@example.com");
    }
}
```

---

### Expected Output

```
Registering patient: Alice
Logged action 'register' for patient: Alice
Sending email to alice@example.com regarding 'register'
Scheduling appointment for: Alice Details: 2024-07-01 10:00AM
Logged action 'schedule' for patient: Alice
Sending email to alice@example.com regarding 'schedule'
```

---

:eye: **Try It:**  
Think about how you would refactor this code to follow the Single Responsibility Principle.

