package solid.srp;

import java.util.Map;

// Abstractions
interface PaymentProcessor {
    void process(double amount);
}

interface FraudChecker {
    boolean isFraudulent(double amount);
}

interface Logger {
    void log(String message);
}

interface Notifier {
    void notify(String customerEmail, double amount);
}

// Concrete Implementations
class PayPalProcessor implements PaymentProcessor {

    @Override
    public void process(double amount) {
        System.out.println("Processing Paypal payment of $" + amount);
    }
}

class StripeProcessor implements PaymentProcessor {
    @Override
    public void process(double amount) {
        System.out.println("Processing Stripe payment of $" + amount);
    }
}

class ThresholdFraudChecker implements FraudChecker {
    public boolean isFraudulent(double amount) {
        if (amount > 10000) {
            System.out.println("Fraud detected: Amount too high");
            return false;
        }
        return true;
    }
}

class ConsoleLogger implements Logger {
    @Override
    public void log(String message) {
        System.out.println("[LOG] " + message);
    }
}

class EmailNotifier implements Notifier {

    @Override
    public void notify(String customerEmail, double amount) {
        System.out.println("Send email to" + customerEmail + " for payment confirmation");
    }
}

// The Orchestrator is compliant with SRP as it has only one reason to change : payment orchestration.
// Orchestrator - acts a mediator between different components
// coordinating their interactions
// without being tightly coupled to their implementations
public class SRPCompliantPaymentService {
    private final Map<String, PaymentProcessor> gateways;
    private final FraudChecker fraudChecker;
    private final Logger logger;
    private final Notifier notifier;

    public SRPCompliantPaymentService(Map<String, PaymentProcessor> gateways, FraudChecker fraudChecker, Logger logger, Notifier notifier) {
        this.gateways = gateways;
        this.fraudChecker = fraudChecker;
        this.logger = logger;
        this.notifier = notifier;
    }

    public void handle(String gateway, double amount, String email ) {
        logger.log("Initiating payment");

        if(fraudChecker.isFraudulent(amount)) {
            logger.log("Fraud blocked payment of $" + amount);
            return;
        }

        PaymentProcessor processor = gateways.get(gateway);
        if(processor == null) {
            logger.log("Invalid gateway: " + gateway);
            return;
        }

        processor.process(amount);

        logger.log("Processed $" + amount + " via " + gateway);
    }
}
