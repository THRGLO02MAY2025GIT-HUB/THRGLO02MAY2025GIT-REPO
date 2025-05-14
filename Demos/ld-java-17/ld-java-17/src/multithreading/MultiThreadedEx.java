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
        System.out.println("Multithreaded Threaded App! ONE process with TWO Threads");
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
