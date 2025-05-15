package designpatterns.creational;
// Thread Safe Singleton with Double-Checked Locking pattern
public class ThreadSafePaymentGatewayService {
    // Step 1 : Declare a private static instance
    // volatile ensures changes made by one thread to be immediately visible to other threads.
    private static volatile ThreadSafePaymentGatewayService instance;

    // Step 2 : Declare a private constructor to prevent direct instantiation from outside
    private ThreadSafePaymentGatewayService() {
        System.out.println("PaymentGatewayService initialized");
    }
    // Double-Checked locking pattern
    // a synchronization pattern
    // reduce the overhead of obtaining a lock by testing the locking criterion twice.


    // Step 3 : Provide a public method to access the instance outside
    public static ThreadSafePaymentGatewayService getInstance() {
        // First Check : Outside the synchronized block - without synchronization
        // to avoid synchronization overhead for subsequent calls
        // not thread-safe on its own
        if (instance == null) {
            // Lock only if instnance is null
            // Synchronization Block :
            // Only entered if teh first check finds null
            // Prevent multiple threads from creating instances simultaneously
            synchronized (ThreadSafePaymentGatewayService.class) {
                // Second check : - inside synchronization block
                // Ensures that instance was not created by another thread while waiting for lock
                // Prevents multiple instance creation in concurrent scenarios.
                if (instance == null) {
                    instance = new ThreadSafePaymentGatewayService();
                }
            }
        }
        return instance;
    }

    // Step 4 : Simulate a method to process payments
    public void processPayment(String transactionId, double amount) {
        System.out.println("Processing payment of $" + amount + " for transaction " + transactionId);
    }

    // Step 5 : Simulate a method to handle refunds
    public void refundPayment(String transactionId) {
        System.out.println("Refunding payment for transaction : " + transactionId);
    }

    public static void main(String[] args) {
        System.out.println("Thread Safe Singleton with Double-Checked Locking pattern");
        // FUNCTIONALLY THOROUGH.....
        ThreadSafePaymentGatewayService service = ThreadSafePaymentGatewayService.getInstance();
        service.processPayment("TXN1234", 10.0);
        service.refundPayment("TXN1234");
        // Verify the singleton
        System.out.println("Service instance :"  + service);

        ThreadSafePaymentGatewayService service1 = ThreadSafePaymentGatewayService.getInstance();
        service.processPayment("TXN1234", 10.0);
        service.refundPayment("TXN1234");
        // Verify the singleton
        System.out.println("Service instance :"  + service1);
    }
}
