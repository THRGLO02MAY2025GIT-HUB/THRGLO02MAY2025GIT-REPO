# 🧩 **Principle:** Single Responsibility Principle (SRP)  
“A class should have only one reason to change.”

---

### ✅ 1. Context: FinTech Payment Gateway Scenario

We are building a `PaymentService` that processes customer payments through different gateways (Stripe, PayPal), logs transactions, sends email notifications, and performs fraud detection.

The initial implementation mixes these responsibilities, violating SRP.

---

### ❌ 2. Violation – Code That Breaks SRP

```java
public class PaymentService {

    public void processPayment(String gateway, double amount, String customerEmail) {
        // Payment processing logic
        if ("Stripe".equals(gateway)) {
            System.out.println("Processing Stripe payment of $" + amount);
        } else if ("PayPal".equals(gateway)) {
            System.out.println("Processing PayPal payment of $" + amount);
        }

        // Fraud detection
        if (amount > 10000) {
            System.out.println("Fraud detected: Amount too high");
            return;
        }

        // Logging
        System.out.println("Logged payment of $" + amount + " via " + gateway);

        // Notification
        System.out.println("Sending email to " + customerEmail + " for payment confirmation");
    }
}
```

---

### ❗ 3. What’s Wrong and Why

💥 **Too many responsibilities:**
- Payment routing
- Logging
- Fraud detection
- Email notification

🧱 Changes in any of those domains force modification of this single class  
❌ Hard to test independently (can’t unit-test fraud logic or email sending)  
❌ Difficult to maintain or extend (e.g., adding a new gateway or logger)

---

### ✅ 4. Refactored SRP-Compliant Version

**Break the class into clearly responsible components:**

```java
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
    void notify(String email, double amount);
}

// Concrete Implementations

class StripeProcessor implements PaymentProcessor {
    public void process(double amount) {
        System.out.println("Stripe processed $" + amount);
    }
}

class PayPalProcessor implements PaymentProcessor {
    public void process(double amount) {
        System.out.println("PayPal processed $" + amount);
    }
}

class ThresholdFraudChecker implements FraudChecker {
    public boolean isFraudulent(double amount) {
        return amount > 10000;
    }
}

class ConsoleLogger implements Logger {
    public void log(String message) {
        System.out.println("[LOG] " + message);
    }
}

class EmailNotifier implements Notifier {
    public void notify(String email, double amount) {
        System.out.println("Sent confirmation to " + email + " for $" + amount);
    }
}

// Orchestrator — has only one reason to change: payment orchestration

class SRPCompliantPaymentService {
    private final Map<String, PaymentProcessor> gateways;
    private final FraudChecker fraudChecker;
    private final Logger logger;
    private final Notifier notifier;

    public SRPCompliantPaymentService(Map<String, PaymentProcessor> gateways,
                                      FraudChecker fraudChecker,
                                      Logger logger,
                                      Notifier notifier) {
        this.gateways = gateways;
        this.fraudChecker = fraudChecker;
        this.logger = logger;
        this.notifier = notifier;
    }

    public void handle(String gateway, double amount, String email) {
        logger.log("Initiating payment");

        if (fraudChecker.isFraudulent(amount)) {
            logger.log("Fraud blocked payment of $" + amount);
            return;
        }

        PaymentProcessor processor = gateways.get(gateway);
        if (processor == null) {
            logger.log("Invalid gateway: " + gateway);
            return;
        }

        processor.process(amount);
        logger.log("Processed $" + amount + " via " + gateway);
        notifier.notify(email, amount);
    }
}
```

---

### 🧪 5. Benefits of SRP Applied

| Aspect           | Before (Violation)         | After (SRP Applied)                |
|------------------|---------------------------|------------------------------------|
| **Testability**  | Hard to mock internals    | Each component unit-testable       |
| **Extensibility**| New feature = edit class  | Add new gateway/logger independently|
| **Reusability**  | Everything is tangled     | Components reusable elsewhere      |
| **Maintainability**| One change → ripple effects | One change → isolated component |

---

### 🧠 6. Best Practices

- Name classes after their responsibility (`StripeProcessor`, not `StripeService`)
- Limit classes to one domain concern (e.g., fraud, logging, messaging)
- Use composition/injection to decouple orchestration logic

---

### ⚠️ 7. Common Anti-patterns to Avoid

- ❌ **God classes:** one class doing routing, emailing, logging, DB ops
- ❌ **Conditional chaos:** long if-else chains for different flows
- ❌ “Helper” classes that do unrelated work (e.g., `Utils` or `CommonService`)

---
