package solid.lsp.violation;

// Some gateways support refunds (like Stripe) and some do not (like Crypto);
abstract class AbstractPaymentGateway {
    abstract void process(double amount, String account);
    abstract void refund(double amount, String account);
}

class StripeGateway extends AbstractPaymentGateway {
    public void process(double amount, String account) {
        System.out.println("Stripe charged $" + amount);
    }
    public void refund(double amount, String account) {
        System.out.println("Stripe refunded $" + amount);
    }
}

class CryptoGateway extends AbstractPaymentGateway {
    public void process(double amount, String account) {
        System.out.println("Crypto sent $" + amount);
    }
    public void refund(double amount, String account) {
        throw new UnsupportedOperationException("Crypto payments are non-refundable");
    }
}