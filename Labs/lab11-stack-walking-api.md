# Lab: Exploring Advanced Stack Walking in Java
30 Minutes

In this lab, you will explore the Stack Walking API introduced in Java 9, focusing on advanced features such as limiting stack depth and retrieving the caller class. You will apply your knowledge on using the `StackWalker` class to inspect the call stack, filter stack frames, and obtain information about the calling class.

## Objective

- Understand the concept of stack walking in Java.
- Limit the depth of stack frames retrieved.
- Retrieve and print the caller class using StackWalker.
- Compare outputs for different stack walking scenarios.

---

## Code Overview

The `StackWalkerAdvancedDemo` class demonstrates how to use the `StackWalker` API for advanced stack inspection. The code includes methods for limiting stack depth and retrieving the caller class, showcasing practical use cases for debugging and logging.

### Key Features

1. **Limiting Stack Depth**:
    - Use `StackWalker` to retrieve a limited number of stack frames.
    - Print method and class names for the top frames.

2. **Retrieving Caller Class**:
    - Use `StackWalker` to obtain the immediate caller class.
    - Demonstrate how this can be used for logging purposes.

---

## Lab Instructions

### Step 1: Limiting Stack Depth

1. Implement the `demonstrateLimitedStack()` method in `ServiceClass`.
2. Use `StackWalker` to walk the stack and limit the output to the top 2 frames.
3. Print each frame in the format: `fully.qualified.ClassName#methodName`.

---

### Step 2: Retrieving Caller Class

1. Implement the `checkCaller()` method in `HelperClass`.
2. Use `StackWalker` to retrieve the immediate caller class.
3. Print the caller class name in the format: `Caller class: fully.qualified.ClassName`.

---

### Step 3: Run and Observe

1. Run the `StackWalkerAdvancedDemo` class.
2. Observe the output for both stack depth limiting and caller class retrieval.
3. Note how the stack frames and caller class are reported.

---

## Deliverables

1. A completed `StackWalkerAdvancedDemo` class with all methods implemented.
2. Console output showing stack frames and caller class for each step.
3. A brief summary of how limiting stack depth and retrieving the caller class can be useful in real-world applications.

---

## Code Template to Start With

```java
package com.demo.java17.m6stackwalking;

import java.util.List;
import java.util.stream.Collectors;

// Main class that orchestrates the demonstration
public class StackWalkerAdvancedDemo {

    public static void main(String[] args) {
        ServiceClass service = new ServiceClass();
        service.executeStackWalkingDemo();
        service.demonstrateCallerClass();
    }
}

// Service class to demonstrate different stack walking scenarios
class ServiceClass {
    private final HelperClass helper = new HelperClass();

    public void executeStackWalkingDemo() {
        System.out.println("=== Starting Stack Walking Demo ===");

        demonstrateLimitedStack();
        demonstrateCallerClass();
    }

    private void demonstrateLimitedStack() {
        System.out.println("\n=== Limiting stack depth to 2 frames ===");
        // TODO: Use StackWalker to print top 2 stack frames
    }

    public void demonstrateCallerClass() {
        System.out.println("\n=== Getting caller class ===");
        helper.checkCaller();
    }
}

// Helper class to demonstrate retained class reference and caller class
class HelperClass {
    public void checkCaller() {
        // TODO: Use StackWalker to print the caller class
    }
}
```

---

### Expected Output

```
=== Starting Stack Walking Demo ===

=== Limiting stack depth to 2 frames ===
com.demo.java17.m6stackwalking.ServiceClass#demonstrateLimitedStack
com.demo.java17.m6stackwalking.ServiceClass#executeStackWalkingDemo

=== Getting caller class ===
Caller class: com.demo.java17.m6stackwalking.ServiceClass

=== Getting caller class ===
Caller class: com.demo.java17.m6stackwalking.ServiceClass
```

---
