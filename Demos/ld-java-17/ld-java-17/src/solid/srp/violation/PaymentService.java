package solid.srp.violation;

public class PaymentService {
    public void processPayment(String gateway, double amount, String email ) {
        // Payment processing logic
        if ("Paypal".equals(gateway)) {
            System.out.println("Processing Paypal payment of $" + amount);
        } else if ("Stripe".equals(gateway)) {
            System.out.println("Processing Stripe payment of $" + amount);
        }

        // Fraud detection
        if(amount > 10000) {
            System.out.println("Fraud detected: Amount too high");
            return;
        }
        // Logging
        System.out.println("Logged payment of $" + amount + " via " + gateway);

        // Notification
        System.out.println("Send email to" + email + " for payment confirmation");
    }

    // -- WORK AREA --
//    public void processPayment(String gateway, double amount) {
//        // Payment processing logic
//        if ("Paypal".equals(gateway)) {
//            System.out.println("Processing Paypal payment of $" + amount);
//        } else if ("Stripe".equals(gateway)) {
//            System.out.println("Processing Stripe payment of $" + amount);
//        } else if ("Square".equals(gateway)) {
//            System.out.println("Processing Square payment of $" + amount);
//        }
//    }
    // -- WORK AREA --
}
