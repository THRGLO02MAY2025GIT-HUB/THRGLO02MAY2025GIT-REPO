# Lab: Designing a Modular Healthcare Service Using JPMS and ServiceLoader

45 Minutes

In this lab, you will practice designing a modular healthcare management system using the Java Platform Module System (JPMS). You will explore how to separate the API, core implementation, and application layers into modules. You will implement and consume services using the `ServiceLoader` pattern, achieving loose coupling and better maintainability.

---

## Objective

- Understand how to structure a Java project using JPMS modules in a healthcare context.
- Define service interfaces in an API module and implement them in a core module.
- Use `ServiceLoader` in the app module to dynamically load service implementations.
- Practice clean modular design and strong encapsulation.

---

## Code Overview

- The `healthcare.api` module defines the `Patient` model and the `PatientService` interface.
- The `healthcare.core` module provides a concrete implementation `PatientServiceImpl` of the `PatientService` interface and publishes it as a service.
- The `healthcare.app` module uses `ServiceLoader` to find and use `PatientService` implementations at runtime.

---

## Starter Code Snippets

<details>
<summary><code>healthcare.api/module-info.java</code></summary>

```java
module healthcare.api {
    exports healthcare.api.model;
    exports healthcare.api.service;
}
```
</details>

<details>
<summary><code>healthcare.api.service.PatientService.java</code></summary>

```java
package healthcare.api.service;

import healthcare.api.model.Patient;
import java.util.List;

public interface PatientService {
    Patient addPatient(String id, String name, int age);
    Patient getPatient(String id);
    List<Patient> listPatients();
}
```
</details>

---

## Lab Instructions

1. **Review Modules:**  
   Examine the module declarations and interface definitions in the API module.

2. **Implement Service:**  
   Complete the `PatientServiceImpl` class in the `healthcare.core` module that manages patient data in memory.

3. **Publish Service:**  
   Declare the service implementation in the `healthcare.core` module using `provides ... with ...`.

4. **Consume Service:**  
   In the `healthcare.app` module, use `ServiceLoader` to load the `PatientService` implementation dynamically.

5. **Run and Test:**  
   Write a simple main method in `HealthcareApplication` to add patients and list them, verifying the modular setup works correctly.

---

## Deliverables

1. Source code for the three modules (`healthcare.api`, `healthcare.core`, `healthcare.app`).
2. A brief explanation of how JPMS modules and service loading improve modularity.
3. Output demonstrating patient additions and listings.

---

### Example Usage

```java
public class HealthcareApplication {
    public static void main(String[] args) {
        PatientService service = ServiceLoader.load(PatientService.class)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No PatientService implementation found"));

        service.addPatient("P001", "Alice", 30);
        service.addPatient("P002", "Bob", 45);

        service.listPatients().forEach(p -> 
            System.out.printf("ID: %s, Name: %s, Age: %d%n", p.getId(), p.getName(), p.getAge())
        );
    }
}
```

---

### Expected Output

```
ID: P001, Name: Alice, Age: 30
ID: P002, Name: Bob, Age: 45
```

---

:eye: **Try It:**  
Consider how separating interface and implementation into modules and using `ServiceLoader` helps with maintainability and scalability in larger systems.