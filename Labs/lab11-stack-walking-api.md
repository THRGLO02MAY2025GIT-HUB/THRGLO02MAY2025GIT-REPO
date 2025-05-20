# Lab: Comparing Traditional Stack Trace and StackWalker API in Java
30 Minutes

In this lab, you will analyze and compare two approaches for inspecting stack traces in Java: the traditional `StackTraceElement[]` method and the modern `StackWalker` API. You will implement methods to extract and print stack frame details for exceptions thrown during simulated transaction processing.

## Objective

- Understand the differences between traditional stack trace analysis and the StackWalker API.
- Implement methods to analyze and print stack frames using both approaches.
- Observe and compare the output for each method.

---

## Code Overview

The `TransactionStackTraceAnalyzer` class simulates transaction processing and demonstrates how to analyze stack traces using both traditional and modern APIs. You will complete the implementation for analyzing stack traces and observe the differences in output.

### Key Features

1. **Traditional Stack Trace Analysis**:
    - Use `StackTraceElement[]` from an exception to print stack frame details.
    - Understand the structure and information available in each frame.

2. **StackWalker API Analysis**:
    - Use `StackWalker` to walk the stack and collect frames.
    - Filter and print relevant stack frame details.

---

## Lab Instructions

### Step 1: Analyze Stack Trace Traditionally

1. Implement the `analyzeStackTraceTraditionally(StackTraceElement[] trace)` method.
2. Print the number of frames being analyzed.
3. For each frame, print:
    - Class Name
    - Method Name
    - File Name
    - Line Number
    - Is Native Method

Format each frame as shown in the expected output.

---

### Step 2: Analyze Stack Trace with StackWalker

1. Review the `analyzeStackWithStackWalker(List<StackWalker.StackFrame> frames)` method.
2. Ensure it filters frames to include only those from `TransactionStackTraceAnalyzer`.
3. Print the details for each frame as in Step 1.

---

### Step 3: Simulate and Observe

1. In the `main` method, call both `simulateTransactionWithTraditional()` and `simulateTransactionWithStackWalker()`.
2. Run the program and observe the output for both approaches.
3. Note the differences in the number and details of stack frames reported.

---

## Deliverables

1. A completed `TransactionStackTraceAnalyzer` class with all methods implemented.
2. Console output showing stack frame details for both traditional and StackWalker approaches.
3. A brief summary comparing the two methods and their usefulness in debugging.

---

## Code Template to Start With

```java
package m6stackwalkingapi;

import java.util.List;
import java.util.stream.Collectors;

public class TransactionStackTraceAnalyzer {
    private static final StackWalker walker = StackWalker.getInstance();

    // -- Called methods (leaf methods)
    private static void processPayment() {
        throw new RuntimeException("Payment processing failed");
    }

    private static void validateTransaction() {
        throw new RuntimeException("Transaction validation failed");
    }

    private static void updateLedger() {
        throw new RuntimeException("Ledger update failed");
    }

    // -- Intermediate methods
    private static void simpleExceptionTrace() {
        try {
            processPayment();
            validateTransaction();
            updateLedger();
        } catch (Exception exception) {
            System.out.println("Exception : " + exception);
            System.out.println("Get Stack Trace");
            for (StackTraceElement frame : exception.getStackTrace()) {
                System.out.println("\t at" + frame.toString());
            }
            System.err.println("Print Stack Trace");
            exception.printStackTrace();
            System.out.println("********************");
        }
    }

    private static void analyzeStackTraceTraditionally(StackTraceElement[] trace) {
        // TODO: Print number of frames and details for each frame as shown in the expected output
    }

    private static void analyzeStackWithStackWalker(List<StackWalker.StackFrame> frames) {
        System.out.println("Analyzing stack trace with " + frames.size() + " frames (StackWalker)");
        frames = frames.stream()
                .filter(frame -> frame.getClassName().contains("TransactionStackTraceAnalyzer"))
                .collect(Collectors.toList());
        for(StackWalker.StackFrame frame : frames) {
            System.out.println("\nStack Frame Details:");
            System.out.println("Class Name:" + frame.getClassName());
            System.out.println("Method Name:" + frame.getMethodName());
            System.out.println("File Name:" + frame.getFileName());
            System.out.println("Line Number:" + frame.getLineNumber());
            System.out.println("Is Native Method:" + frame.isNativeMethod());
            System.out.println("***********");
        }
    }

    private static void simulateTransactionWithTraditional() {
        try {
            processPayment();
            validateTransaction();
            updateLedger();
        } catch (Exception exception) {
            StackTraceElement[] trace = exception.getStackTrace();
            analyzeStackTraceTraditionally(trace);
        }
    }

    private static void simulateTransactionWithStackWalker() {
        try {
            processPayment();
            validateTransaction();
            updateLedger();
        } catch (Exception exception) {
            List<StackWalker.StackFrame> frames = walker.walk(stream -> stream
                    .collect(Collectors.toList()));
            analyzeStackWithStackWalker(frames);
        }
    }

    // -- Entry point (calling method) --
    public static void main(String[] args) {
        simulateTransactionWithTraditional();
        simulateTransactionWithStackWalker();
    }
}
```

---

### Expected Output

```
Analyzing stack trace traditionally with 3 frames

Stack Frame Details:
Class Name: com.demo.java17.m6stackwalking.TransactionStackTraceAnalyzer
Method Name: processPayment
File Name: TransactionStackTraceAnalyzer.java
Line Number: 11
Is Native Method: false

Stack Frame Details:
Class Name: com.demo.java17.m6stackwalking.TransactionStackTraceAnalyzer
Method Name: simulateTransactionWithTraditional
File Name: TransactionStackTraceAnalyzer.java
Line Number: 83
Is Native Method: false

Stack Frame Details:
Class Name: com.demo.java17.m6stackwalking.TransactionStackTraceAnalyzer
Method Name: main
File Name: TransactionStackTraceAnalyzer.java
Line Number: 131
Is Native Method: false
***********

Analyzing stack trace with 2 frames (StackWalker)

Stack Frame Details:
Class Name: com.demo.java17.m6stackwalking.TransactionStackTraceAnalyzer
Method Name: simulateTransactionWithStackWalker
File Name: TransactionStackTraceAnalyzer.java
Line Number: 98
Is Native Method: false

Stack Frame Details:
Class Name: com.demo.java17.m6stackwalking.TransactionStackTraceAnalyzer
Method Name: main
File Name: TransactionStackTraceAnalyzer.java
Line Number: 132
Is Native Method: false
***********
```

---

:eye: Try It : 
Like the traditional method, printing 3 frames make StackWalker print the same 3 frames too.