package solid.lsp;


// Core Abstraction
interface PaymentProcessor {
    void process(double amount, String account);
}
// Refundable Abstraction
interface RefundablePaymentProcessor extends PaymentProcessor {
    public void refund(double amount, String account);
}
// Stripe supports refunds
class StripeProcessor implements RefundablePaymentProcessor {
    public void process(double amount, String account) {
        System.out.println("Stripe charged $" + amount);
    }
    public void refund(double amount, String account) {
        System.out.println("Stripe refunded $" + amount);
    }
}
// Crypto does NOT support refunds
class CryptoProcessor implements PaymentProcessor {
    public void process(double amount, String account) {
        System.out.println("Crypto sent $" + amount);
    }
}

class AdvancedCryptoProcessor implements PaymentProcessor {
    public void process(double amount, String account) {
        System.out.println("Advanced Crypto sent $" + amount);
    }
}