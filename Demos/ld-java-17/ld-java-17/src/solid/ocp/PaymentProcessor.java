package solid.ocp;

import java.util.HashMap;
import java.util.Map;

// STEP 1: Abstraction
interface PaymentProcessor {
    void process(double amount, String account);
}

// STEP 2: Extension Points (New processors)
class StripeProcessor implements PaymentProcessor {
    public void process(double amount, String account) {
        System.out.println("Stripe processed $" + amount + " for " + account);
    }
}

class PayPalProcessor implements PaymentProcessor {
    public void process(double amount, String account) {
        System.out.println("PayPal processed $" + amount + " for " + account);
    }
}

class CryptoProcessor implements PaymentProcessor {
    public void process(double amount, String account) {
        System.out.println("Crypto transfer: $" + amount + " to wallet " + account);
    }
}

// Lab : Add AmazonPay Processor
// STEP 3 : Registry - Open for extension via injection
// Open for extension because it
// 1. allows adding new payment processors without coding changes
// 2. supports runtime registration of processors
// 3. no modification of existing code needed.
class PaymentGatewayRegistry {
    private final Map<String, PaymentProcessor> gatewayMap = new HashMap<>();

    public void register(String key, PaymentProcessor processor) {
        gatewayMap.put(key.toLowerCase(), processor);
    }

    public PaymentProcessor resolve(String key) {
        return gatewayMap.get(key.toLowerCase());
    }
}

// STEP 4 : Orcherstrator (closed for modification)
// Closed for modification because
// 1. The core payment flow remains unchanged
// 2. New processors don't require orchestration changes
// 3. Business logic is encapsulated
class OCPCompliantPaymentService {
    private final PaymentGatewayRegistry registry;

    public OCPCompliantPaymentService(PaymentGatewayRegistry registry) {
        this.registry = registry;
    }

    public void handle(String gateway, double amount, String account) {
        PaymentProcessor processor = registry.resolve(gateway);
        if(processor == null) {
            System.out.println("Unsupported payment gateway : " + gateway);
            return;
        }
        processor.process(amount, account);
    }
}

// STEP 5 : Usage
class OCPMain {
    public static void main(String[] args) {
        PaymentGatewayRegistry registry = new PaymentGatewayRegistry();
        registry.register("stripe", new StripeProcessor());
        registry.register("paypal", new PayPalProcessor());
        registry.register("crypto", new CryptoProcessor());
        //Lab : register Amazon Pay
        OCPCompliantPaymentService service = new OCPCompliantPaymentService(registry);

        service.handle("stripe", 500, "acct-001");
        service.handle("crypto", 1200, "wallet-xyz");
        //Lab make payment using Amazon Pay
    }
}
