package m6stackwalkingapi;

public class StackTraceSimple {
    public static void main(String[] args) {
        StackTraceElement[] stackTraceElements =   Thread.currentThread().getStackTrace();
        System.out.println(stackTraceElements);

        try {
            new StackTraceSimple().makePayment();
        }
        catch (Exception exception) {
         StackTraceElement[] stackTraceElements1 =   Thread.currentThread().getStackTrace();
            System.out.println(stackTraceElements1);
        }
    }

    private void makePayment() {
        System.out.println("Making payment ");
        processTransaction();
    }

    private void processTransaction() {
        System.out.println("Processing transaction");
        validatePayment();
        logTransaction();
    }

    private void validatePayment() {
        System.out.println("Validating payment");
    }
    private void logTransaction() {
        System.out.println("Logging transaction  .... error.....");
        try {
            throw new RuntimeException("Logging failure as database is down!");
        } catch (Exception exception) {
            StackTraceElement[] stackTraceElementsFromThrowable = exception.getStackTrace();
            System.out.println(stackTraceElementsFromThrowable);
            StackTraceElement[] stackTraceElementsFromCurrentThread =   Thread.currentThread().getStackTrace();
            System.out.println(stackTraceElementsFromCurrentThread);
        }
    }
}
