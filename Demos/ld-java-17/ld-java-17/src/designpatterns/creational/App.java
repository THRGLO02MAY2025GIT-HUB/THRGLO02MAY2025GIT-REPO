package designpatterns.creational;

public class App {
    // Step 6: Write Main to simulate usage
    public static void main(String[] args) {
        // FUNCTIONALLY THOROUGH.....
        PaymentGatewayService service = PaymentGatewayService.getInstance();
        service.processPayment("TXN1234", 10.0);
        service.refundPayment("TXN1234");
        // Verify the singleton
        System.out.println("Service instance :"  + service);

        PaymentGatewayService service1 = PaymentGatewayService.getInstance();
        service.processPayment("TXN1234", 10.0);
        service.refundPayment("TXN1234");
        // Verify the singleton
        System.out.println("Service instance :"  + service1);

    }
}
