package designpatterns.creational;
// Try to convert this singleton to a Tread Safe Singleton
public class PaymentGatewayService extends Object {
    // Step 1 : Declare a private static instance
    private static  PaymentGatewayService instance;

    // Step 2 : Declare a private constructor to prevent direct instantiation from outside
    private PaymentGatewayService() {
        System.out.println("PaymentGatewayService initialized");
    }

    // Step 3 : Provide a public method to access the instance outside
    public static PaymentGatewayService getInstance() {
        if (instance == null) {
            instance = new PaymentGatewayService();
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
}
