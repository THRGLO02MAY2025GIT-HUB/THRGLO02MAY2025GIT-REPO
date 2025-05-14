# Lab 9: Multithreading in Java  
30 Minutes  

In this lab, you will explore multithreading in Java by creating and managing multiple threads. You will refactor code to improve thread naming, ensure proper thread synchronization, and handle exceptions effectively.  

## Objective  

- Understand the basics of multithreading in Java.  
- Refactor code to rename threads dynamically.  
- Ensure the main thread waits for other threads to complete execution.  
- Implement a delay after thread execution.  
- Handle checked exceptions gracefully.  

---  

## Code Overview  

The program demonstrates multithreading by creating two threads: `EvenThread` and `OddThread`. The `EvenThread` prints even numbers, while the `OddThread` prints odd numbers. The main thread waits for both threads to complete before printing a final message.  

### Key Features  

1. **Thread Naming**:  
    - Rename the `EvenThread` to "Thread-even".  
    - Ensure all threads have meaningful names.  

2. **Thread Synchronization**:  
    - Use the `join` method to ensure the main thread waits for `EvenThread` and `OddThread` to complete.  

3. **Delay After Execution**:  
    - Add a 5-second delay after printing "End of the program!".  
    - Print "Thank you!!!" after the delay.  

4. **Exception Handling**:  
    - Catch and handle checked exceptions such as `InterruptedException`.  

5. **OddThread Implementation**:  
    - Refactor the `OddThread` class to implement the `Runnable` interface.  

---  

## Lab Instructions  

### Step 1: Refactor Thread Naming  

1. Rename the running `EvenThread` to "Thread-even".  
2. Ensure the running `OddThread` has a meaningful name, such as "Thread-odd".  

---  

### Step 2: Synchronize Threads  

1. Use the `join` method to ensure the main thread waits for `EvenThread` and `OddThread` to complete execution.  
2. Verify that "End of the program!" is printed only after both threads finish.  

---  

### Step 3: Add Delay and Final Message  

1. Add a 5-second delay after printing "End of the program!".  
2. Print "Thank you!!!" after the delay.  

---  

### Step 4: Handle Exceptions  

1. Catch and handle `InterruptedException` during thread synchronization and delay.  
2. Print appropriate error messages if exceptions occur.  

---  

### Step 5: Implement OddThread  

1. Refactor the `OddThread` class to implement the `Runnable` interface.  
2. Ensure the `OddThread` prints odd numbers from 1 to 50.  

---  

## Deliverables  

1. A refactored `EvenThread` with a meaningful name.  
2. A properly implemented `OddThread` using the `Runnable` interface.  
3. A synchronized main thread that waits for other threads to complete.  
4. A delay and final message after thread execution.  
5. Exception handling for thread operations.  

---  

## Code Template to Start With  

```java
package multithreading;

// Refactor code :
// Change even thread name to Thread-even
// The main should print end of program only after all the threads EvenThread and OddThread completes execution. Hint : Thead API for methods in Thread class
// After printing "End of the program!" wait for 5 seconds, print "Thankyou!!!" and the program halts.
// Catch the appropriate CheckedExceptions.
// Refactor the OddThread class to make it a thread.
// Implement the Odd thead.
// The program should have total of three threads to do the work.

public class MultiThreadedEx {
    public static void main(String[] args) {
        System.out.println("Multithreaded Threaded App! ONE process with Three Threads");
        Thread currentThread = Thread.currentThread();
        currentThread.setName("Thread-main");
        System.out.println(currentThread.getName());
        EvenThread evenThread = new EvenThread();
        evenThread.start();
//        compute.odd();
//        for(int i = 0; i<500000000; i++) {
//            String justToAddSomeProcessingLoad = "" + i;
//        }
        System.out.println();
        // join evenThread and oddThread
        // sleep for 5 seconds
        System.out.println("End of the program! " + currentThread.getName());
    }
}
class Compute1 {
    public void even(){
        for (int i = 2; i <= 50; i+=2) {
            System.out.println("EVEN " + i + " " + Thread.currentThread().getName()) ;
        }
    }
    public void odd(){
        for (int i = 1; i <= 50; i+=2) {
            System.out.println("ODD " + i + " " + Thread.currentThread().getName()) ;
        }
    }
}

class ComputeOdd {
    public void odd(){
        for (int i = 1; i <= 50; i+=2) {
            System.out.println("ODD " + i + " " + Thread.currentThread().getName()) ;
        }
    }
}

class EvenThread extends Thread {
    @Override
    public void run() {
        Compute1 compute = new Compute1();
        compute.even();
    }
}

// Practice : Create the odd thread
class OddThread  extends ComputeOdd implements Runnable {
    @Override
    public void run() {
        odd();
    }
}

```  

Expected Output:  
```
Multithreaded Threaded App! ONE process with THREEE Threads  
Thread-main  
EVEN 2 Thread-even  
EVEN 4 Thread-even  
...  
ODD 1 Thread-odd  
ODD 3 Thread-odd  
...  
End of the program! Thread-main  
Thank you!!!  
```  
---  