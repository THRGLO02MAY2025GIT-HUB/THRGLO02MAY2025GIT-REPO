package m6stackwalkingapi;

import java.util.List;
import java.util.stream.Collectors;

import static java.nio.file.Files.walk;

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
        // Lab code here.....
    }

    private static void analyzeStackWithStackWalker(List<StackWalker.StackFrame> frames) {
        System.out.println("Analyzing stack trace with " + frames.size() + " frames (StrackWalker)");
        frames = frames.stream()
                .filter(frame -> frame.getClassName().contains("TransactionStackTraceAnalyzer"))
                .collect(Collectors.toList());
        for(StackWalker.StackFrame frame : frames) {
            System.out.println("\n Stack Frame Details:");
            System.out.println("Class Name:" + frame.getClassName());
            System.out.println("Method Name:" + frame.getMethodName());
            System.out.println("File Name:" + frame.getFileName());
            System.out.println("Line Number:" + frame.getLineNumber());
            System.out.println("Is Native Method:" + frame.isNativeMethod());
            System.out.println("********************");
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
//        simpleExceptionTrace();
        simulateTransactionWithStackWalker();
    }
}
