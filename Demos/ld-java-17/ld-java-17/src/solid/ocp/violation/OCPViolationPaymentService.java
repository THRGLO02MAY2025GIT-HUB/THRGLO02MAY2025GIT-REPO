package solid.ocp.violation;
// Violates OCP
// Every new Method of Payment means modifying this method
// Higher risk of introducing bugs in tested logic
// Violation of separation of concerns - business logic buried inside the conditions
public class OCPViolationPaymentService {
    public void process(String gateway, double amount, String account) {
        // Amazon pay here....
        if ("Stripe".equalsIgnoreCase(gateway)) {
            System.out.println("Processing via Stripe: $" + amount);
        } else if ("PayPal".equalsIgnoreCase(gateway)) {
            System.out.println("Processing via PayPal: $" + amount);
        } else if ("Crypto".equalsIgnoreCase(gateway)) {
            System.out.println("Processing via Crypto Wallet: $" + amount);
        } else {
            throw new IllegalArgumentException("Unsupported gateway: " + gateway);
        }
    }
}