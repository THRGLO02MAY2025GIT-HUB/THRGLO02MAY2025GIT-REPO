package m6stackwalkingapi;

import java.util.List;
import java.util.stream.Collectors;


public class StackTracePerformanceTest {
    private static final int ITERATIONS = 100000;
    private static final StackWalker walker = StackWalker.getInstance();

    public static void main(String[] args) {
        // Warp-up JVM
        for (int i = 0; i < 1000; i++){
            getTraditionalStackTrace();
            getStackWalkerTrace();
        }

        // Actual benchmarking
        long traditionalStart = System.nanoTime();
        for(int i = 0; i < ITERATIONS; i++){
            simulateTransactionWithTraditional();
        }
        long traditionalTime = System.nanoTime() - traditionalStart;

        long walkerStart = System.nanoTime();
        for(int i = 0; i < ITERATIONS; i++){
            simulateTransactionWithStackWalker();
        }
        long walkerTime = System.nanoTime() - walkerStart;

        //Print results
        System.out.println("Traditional approach  (ms): " + traditionalTime / 1_000_000 );
        System.out.println("Stack Walker approach  (ms): " + walkerTime / 1_000_000 );
        System.out.printf("Performance improvement : %.2f%% (ms):" ,(traditionalTime -walkerTime)* 100.0 / traditionalTime);
    }
    private static StackTraceElement[] getTraditionalStackTrace() {
        return Thread.currentThread().getStackTrace();
    }

    private static List<StackWalker.StackFrame> getStackWalkerTrace() {
        return walker.walk( frames -> frames
                .collect(Collectors.toList()));
    }

    private static void simulateTransactionWithTraditional() {
        try {
            processPayment();
            validateTransaction();
            updateLedger();
        }
        catch (Exception exception){
            walker.walk( frames -> frames
                    .collect(Collectors.toList()));
        }
    }

    private static void  simulateTransactionWithStackWalker() {
        try {
            processPayment();
            validateTransaction();
            updateLedger();
        }
        catch (Exception exception){
            walker.walk( frames -> frames
                    .collect(Collectors.toList()));
        }
    }
    // Simulated financial operations
    private static void processPayment() {
        throw new RuntimeException("Payment processing failed");
    }

    private static void validateTransaction() {
        throw new RuntimeException("Transaction validation failed");
    }

    private static void updateLedger() {
        throw new RuntimeException("Ledger update failed");
    }

    private static void analyzeStackTrace(StackTraceElement[] trace) {
        // Actually process the stack trace.
    }
}
