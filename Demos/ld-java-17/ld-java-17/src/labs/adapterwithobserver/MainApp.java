package labs.adapterwithobserver;

public class MainApp {
    public static void main(String[] args) {
        //Create the payment gateways
        PayPalAPI payPalAPI = new PayPalAPI();
        StripeAPI stripeAPI = new StripeAPI();

        //Create adapters for each payment gateway
        PaymentProcessor payPalProcessor = new PayPalAdapter(payPalAPI);
        PaymentProcessor stripeProcessor = new StripeAdapter(stripeAPI);

        //Create the payment status subject
        PaymentStatusSubject paymentStatusSubject1 = new PaymentStatusSubject();
        PaymentStatusSubject paymentStatusSubject2 = new PaymentStatusSubject();

        //Create observers (users)
        User user1 = new User("Mr.Passion");
        User user2 = new User("Ms.Grace");

        //Add observers to the payment status subject
        paymentStatusSubject1.addObserver(user1);
        paymentStatusSubject2.addObserver(user2);

        //Create payment services for each payment processor
        PaymentService payPalService = new PaymentService(payPalProcessor, paymentStatusSubject1);
        PaymentService stripeService = new PaymentService(stripeProcessor, paymentStatusSubject2);

        //Process payments through different payment gateways
        System.out.println("Making payment through PayPal:");
        payPalService.processPayment(100.00);

        System.out.println("Making payment through Stripe:");
        stripeService.processPayment(200.00);
    }
}
