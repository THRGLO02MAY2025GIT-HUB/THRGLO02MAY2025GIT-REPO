# Lab: Identifying DIP Violation in a Healthcare Service

30 Minutes

In this lab, you will analyze a healthcare domain example that violates the Dependency Inversion Principle (DIP). You will see how high-level modules depending directly on low-level modules can lead to rigid and hard-to-maintain code. This exercise follows the same pattern as the DIP violation report generator example.

---

## Objective

- Understand what constitutes a DIP violation in a healthcare context.
- Identify the risks of tightly coupling high-level logic to concrete implementations.
- Prepare for refactoring towards DIP compliance.

---

## Code Overview

The `DatabasePatientStorage` class provides a method to store patient data. The `PatientAdmissionService` class directly creates and uses a `DatabasePatientStorage` instance, resulting in a concrete dependency and violating DIP.

---

## Code Example

```java
package com.demo.solid.dip.violation;

class DatabasePatientStorage {
    public void store(String patientName, String details) {
        System.out.println("[DB] Stored patient: " + patientName + " Details: " + details);
    }
}

class PatientAdmissionService {
    public void admitPatient(String patientName, String details) {
        DatabasePatientStorage storage = new DatabasePatientStorage(); // ❌ Concrete dependency
        storage.store(patientName, details);
    }
}
```

---

## Lab Instructions

1. **Review the Code:**  
   Examine how `PatientAdmissionService` depends on `DatabasePatientStorage`.

2. **Run and Observe:**  
   Write a simple main method to admit a patient and observe the output.

3. **Discuss Drawbacks:**  
   List the problems that may arise from this design (e.g., hard to switch storage, violates DIP, difficult to test).

4. **Prepare for Refactoring:**  
   Suggest how you might refactor this code to be DIP-compliant (e.g., introduce an abstraction for storage).

---

## Deliverables

1. A description of the dependencies in the current design.
2. Example output from running the code.
3. A brief summary of why this design is problematic.
4. Suggestions for refactoring towards DIP compliance.

---

### Example Usage

```java
public class Main {
    public static void main(String[] args) {
        PatientAdmissionService admission = new PatientAdmissionService();
        admission.admitPatient("Alice", "Routine checkup");
    }
}
```

---

### Expected Output

```
[DB] Stored patient: Alice Details: Routine checkup
```

---

:eye: **Try It:**  
Think about how you would refactor this code to follow the Dependency Inversion Principle.

