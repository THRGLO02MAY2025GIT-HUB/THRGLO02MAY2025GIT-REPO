# Lab: Identifying LSP Violation in a Healthcare Service

30 Minutes

In this lab, you will analyze a healthcare domain example that violates the Liskov Substitution Principle (LSP). You will see how subclassing can lead to unexpected behavior when a subclass does not fully support the contract of its superclass. This exercise follows the same pattern as the LSP violation payment gateway example.

---

## Objective

- Understand what constitutes an LSP violation in a healthcare context.
- Identify the risks of substituting subclasses that do not honor the superclass contract.
- Prepare for refactoring towards LSP compliance.

---

## Code Overview

The `AbstractPatientRecordService` class below defines two abstract methods: `admit` and `discharge`. Two subclasses implement these methods: `GeneralPatientService` supports both operations, while `EmergencyPatientService` throws an exception for `discharge`, violating LSP.

---

## Code Example

```java
package com.demo.solid.lsp.violation;

abstract class AbstractPatientRecordService {
    abstract void admit(String patientName, String details);
    abstract void discharge(String patientName);
}

class GeneralPatientService extends AbstractPatientRecordService {
    public void admit(String patientName, String details) {
        System.out.println("Admitting patient: " + patientName + " Details: " + details);
    }
    public void discharge(String patientName) {
        System.out.println("Discharging patient: " + patientName);
    }
}

class EmergencyPatientService extends AbstractPatientRecordService {
    public void admit(String patientName, String details) {
        System.out.println("Emergency admit: " + patientName + " Details: " + details);
    }
    public void discharge(String patientName) {
        throw new UnsupportedOperationException("Emergency patients cannot be discharged directly");
    }
}
```

---

## Lab Instructions

1. **Review the Code:**  
   Examine the `AbstractPatientRecordService` and its subclasses. Identify how each handles `admit` and `discharge`.

2. **Run and Observe:**  
   Write a simple main method to call `admit` and `discharge` on both services and observe the output.

3. **Discuss Drawbacks:**  
   List the problems that may arise from this design (e.g., violates LSP, risk of runtime exceptions, breaks substitutability).

4. **Prepare for Refactoring:**  
   Suggest how you might refactor this class hierarchy to be LSP-compliant (e.g., separating interfaces or using composition).

---

## Deliverables

1. A list of operations supported by each subclass.
2. Example output from running the code with both services.
3. A brief summary of why this design is problematic.
4. Suggestions for refactoring towards LSP compliance.

---

### Example Usage

```java
public class Main {
    public static void main(String[] args) {
        AbstractPatientRecordService general = new GeneralPatientService();
        AbstractPatientRecordService emergency = new EmergencyPatientService();

        general.admit("Alice", "Routine checkup");
        general.discharge("Alice");

        emergency.admit("Bob", "Accident case");
        emergency.discharge("Bob"); // This will throw an exception
    }
}
```

---

### Expected Output

```
Admitting patient: Alice Details: Routine checkup
Discharging patient: Alice
Emergency admit: Bob Details: Accident case
Exception in thread "main" java.lang.UnsupportedOperationException: Emergency patients cannot be discharged directly
    at com.demo.solid.lsp.violation.EmergencyPatientService.discharge(EmergencyPatientService.java:...)
    ...
```

---

:eye: **Try It:**  
Think about how you would refactor this code to follow the Liskov Substitution Principle.

