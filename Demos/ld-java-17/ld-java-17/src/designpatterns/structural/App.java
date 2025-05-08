package designpatterns.structural;

public class App {
    public static void main(String[] args) {
        // Create instances of the API's
        PayPalAPI payPalAPI = new PayPalAPI();

        // Create adapters for each gateway
        PaymentProcessor payPalProcessor = new PayPalAdapter(payPalAPI);

        // Create PaymentService for each payment processor
        PaymentService payPalService = new PaymentService(payPalProcessor);

        // Process the payment through different payment gateways
        System.out.println("Making payment through PayPal: ");
        payPalService.makePayment(100.0);

        //IP : Implement the other payment gateways like Stripe API, Square, AmazonPay.
    }
}
