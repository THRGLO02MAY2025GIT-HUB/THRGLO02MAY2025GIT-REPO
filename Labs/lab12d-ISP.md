# Lab: Identifying ISP Violation in a Healthcare Notification Service

30 Minutes

In this lab, you will analyze a healthcare domain example that violates the Interface Segregation Principle (ISP). You will see how forcing classes to implement methods they do not support can lead to fragile designs and runtime errors. This exercise follows the same pattern as the ISP violation notifier example.

---

## Objective

- Understand what constitutes an ISP violation in a healthcare context.
- Identify the risks of "fat" interfaces that force unnecessary method implementations.
- Prepare for refactoring towards ISP compliance.

---

## Code Overview

The `PatientNotifier` interface below defines three methods: `notifyByEmail`, `notifyBySMS`, and `notifyByPager`. Two classes implement this interface: `EmailNotifier` only supports email notifications, while `FullNotifier` supports all three. `EmailNotifier` throws exceptions for unsupported operations, violating ISP.

---

## Code Example

```java
package com.demo.solid.isp.violation;

interface PatientNotifier {
    void notifyByEmail(String address, String message);
    void notifyBySMS(String phoneNumber, String message);
    void notifyByPager(String pagerId, String message);
}

// Notifier that only supports email
class EmailNotifier implements PatientNotifier {
    public void notifyByEmail(String address, String message) {
        System.out.println("[Email] To: " + address + ", Msg: " + message);
    }

    public void notifyBySMS(String phoneNumber, String message) {
        throw new UnsupportedOperationException("SMS not supported");
    }

    public void notifyByPager(String pagerId, String message) {
        throw new UnsupportedOperationException("Pager not supported");
    }
}

// Notifier that supports all methods
class FullNotifier implements PatientNotifier {
    public void notifyByEmail(String address, String message) {
        System.out.println("[Email] To: " + address + ", Msg: " + message);
    }

    public void notifyBySMS(String phoneNumber, String message) {
        System.out.println("[SMS] To: " + phoneNumber + ", Msg: " + message);
    }

    public void notifyByPager(String pagerId, String message) {
        System.out.println("[Pager] To: " + pagerId + ", Msg: " + message);
    }
}
```

---

## Lab Instructions

1. **Review the Code:**  
   Examine the `PatientNotifier` interface and its implementations. Identify which methods are supported by each class.

2. **Run and Observe:**  
   Write a simple main method to call all notification methods on both notifiers and observe the output.

3. **Discuss Drawbacks:**  
   List the problems that may arise from this design (e.g., violates ISP, risk of runtime exceptions, unnecessary method implementations).

4. **Prepare for Refactoring:**  
   Suggest how you might refactor this interface to be ISP-compliant (e.g., splitting interfaces by notification type).

---

## Deliverables

1. A list of notification methods supported by each implementation.
2. Example output from running the code with both notifiers.
3. A brief summary of why this design is problematic.
4. Suggestions for refactoring towards ISP compliance.

---

### Example Usage

```java
public class Main {
    public static void main(String[] args) {
        PatientNotifier emailNotifier = new EmailNotifier();
        PatientNotifier fullNotifier = new FullNotifier();

        emailNotifier.notifyByEmail("alice@hospital.com", "Your appointment is confirmed.");
        // The following will throw exceptions:
        // emailNotifier.notifyBySMS("1234567890", "Reminder");
        // emailNotifier.notifyByPager("PAGER123", "Urgent update");

        fullNotifier.notifyByEmail("bob@hospital.com", "Your test results are ready.");
        fullNotifier.notifyBySMS("9876543210", "Please call your doctor.");
        fullNotifier.notifyByPager("PAGER456", "Emergency alert.");
    }
}
```

---

### Expected Output

```
[Email] To: alice@hospital.com, Msg: Your appointment is confirmed.
[Email] To: bob@hospital.com, Msg: Your test results are ready.
[SMS] To: 9876543210, Msg: Please call your doctor.
[Pager] To: PAGER456, Msg: Emergency alert.
Exception in thread "main" java.lang.UnsupportedOperationException: SMS not supported
    at com.demo.solid.isp.violation.EmailNotifier.notifyBySMS(EmailNotifier.java:...)
    ...
```

---

:eye: **Try It:**  
Think about how you would refactor this code to follow the Interface Segregation Principle.

