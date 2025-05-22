# 🧩 Principle: Open-Closed Principle (OCP)  
“Software entities (classes, modules, functions, etc.) should be open for extension, but closed for modification.”

---

### ✅ 1. Context: FinTech – Adding New Payment Types

We have a payment gateway system that currently supports Stripe and PayPal. A new business requirement arrives: support Crypto payments.

A non-OCP-compliant design would force us to edit existing `PaymentService` logic every time we add a new payment method — risking regressions and tightly coupling concerns.

---

### ❌ 2. Violation – Not Open for Extension

```java
public class OCPViolationPaymentService {
    public void process(String gateway, double amount, String account) {
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
```

---

#### ❗ Why This Violates OCP

- 🚫 Every new payment method means modifying this method  
- ⚠️ Higher risk of introducing bugs in tested logic  
- 🧱 Violates separation of concerns — business logic buried in conditions

---

### ✅ 3. Refactored – OCP Applied Using Strategy Pattern

We define a common interface for all processors and register them in a gateway registry.

```java
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

// STEP 3: Registry – Open for extension via injection
class PaymentGatewayRegistry {
    private final Map<String, PaymentProcessor> gatewayMap = new HashMap<>();

    public void register(String key, PaymentProcessor processor) {
        gatewayMap.put(key.toLowerCase(), processor);
    }

    public PaymentProcessor resolve(String key) {
        return gatewayMap.get(key.toLowerCase());
    }
}

// STEP 4: Orchestrator (closed for modification)
class OCPCompliantPaymentService {
    private final PaymentGatewayRegistry registry;

    public OCPCompliantPaymentService(PaymentGatewayRegistry registry) {
        this.registry = registry;
    }

    public void handle(String gateway, double amount, String account) {
        PaymentProcessor processor = registry.resolve(gateway);
        if (processor == null) {
            System.out.println("Unsupported payment gateway: " + gateway);
            return;
        }
        processor.process(amount, account);
    }
}

// STEP 5: Usage
class OCPDemo {
    public static void main(String[] args) {
        PaymentGatewayRegistry registry = new PaymentGatewayRegistry();
        registry.register("stripe", new StripeProcessor());
        registry.register("paypal", new PayPalProcessor());
        registry.register("crypto", new CryptoProcessor());

        OCPCompliantPaymentService service = new OCPCompliantPaymentService(registry);

        service.handle("stripe", 500, "acct-001");
        service.handle("crypto", 1200, "wallet-xyz");
    }
}
```

---

### 📈 4. Benefits of OCP in FinTech Systems

| Benefit                | Explanation                                              |
|------------------------|---------------------------------------------------------|
| ✅ Safe extension      | Add new payment types without modifying orchestration logic |
| 🔧 Easier to test      | Each processor testable in isolation                    |
| ♻️ Encourages reusability | Processors can be reused in batch jobs or scheduled flows |
| 🧼 Cleaner architecture | Avoids fragile if-else blocks in core business logic    |

---

### 🧠 5. Best Practices for OCP

- Use interfaces or abstract classes as extension points
- Register concrete implementations via factory, DI, or service loader
- Write unit tests per extension, not for control flow
- Favor composition over conditionals

---

### 🚫 6. Common Anti-Patterns (Violations)

- `if/else` or `switch-case` on string keys
- Editing orchestrator every time a new variation is introduced
- Class enums with behavioral branching

---

### 🧩 Integration with SRP

The `PaymentProcessor` interface is SRP-aligned (one responsibility: process a payment) and OCP-aligned (easy to extend). The orchestrator is now clean and resilient.