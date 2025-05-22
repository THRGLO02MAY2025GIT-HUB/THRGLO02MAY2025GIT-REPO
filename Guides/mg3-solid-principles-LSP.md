# 🧩 Principle: Liskov Substitution Principle (LSP)

> “Objects of a superclass should be replaceable with objects of its subclasses without breaking the application.”

---

## ✅ 1. Context: FinTech – Payment and Refunds

In our gateway system, some processors (like **Stripe**) support refunds, while others (like **Crypto**) do not.

A violation of LSP occurs when we use a parent class/interface in a context that assumes behavior which a subclass can’t fulfill, e.g., calling `refund()` on a processor that doesn’t implement it.

---

## ❌ 2. Violation – Bad Inheritance Hierarchy

```java
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
```

**🚨 What’s Wrong?**

- `CryptoGateway` violates the contract of its base class.
- Calling `refund()` works for `StripeGateway`, but fails for `CryptoGateway`.
- Breaks substitutability — causes runtime failures.

---

## ✅ 3. LSP-Compliant Redesign – Split Interface Hierarchy

We split responsibilities using interfaces to separate refundable and non-refundable behaviors.

```java
// Core abstraction
interface PaymentProcessor {
    void process(double amount, String account);
}

// Refundable abstraction
interface RefundablePaymentProcessor extends PaymentProcessor {
    void refund(double amount, String account);
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
```

---

## 💡 4. LSP-Compliant Use Case

```java
class RefundService {
    public void issueRefund(RefundablePaymentProcessor processor, double amount, String account) {
        processor.refund(amount, account);
    }
}

class LSPDemo {
    public static void main(String[] args) {
        RefundService service = new RefundService();

        RefundablePaymentProcessor stripe = new StripeProcessor();
        service.issueRefund(stripe, 150, "acct-001");

        // ❌ This won't compile — and that's good!
        // PaymentProcessor crypto = new CryptoProcessor();
        // service.issueRefund(crypto, 100, "wallet-xyz");
    }
}
```

---

## 📈 5. Benefits of LSP in Payment Systems

| Benefit                  | Explanation                                                        |
|--------------------------|--------------------------------------------------------------------|
| ✅ Safe substitutability | Every subclass behaves exactly like the base contract              |
| 🛡️ Compile-time safety   | Cannot call unsupported methods on incompatible processors         |
| 🔧 Clean separation      | Refund vs non-refund logic is clearly separated                    |
| 📦 Easier to test/extend | Add new gateways without polluting core contract                   |

---

## 🧠 6. Best Practices

- Split interfaces when behaviors differ (`RefundablePaymentProcessor` vs `PaymentProcessor`)
- Avoid optional method implementations (throwing `UnsupportedOperationException`)
- Don’t use inheritance just because “it looks similar” — use capability-based modeling
- Keep base types minimal and extensible

---

## 🚫 7. Anti-Patterns That Break LSP

- ❌ Subclasses that override methods to throw exceptions
- ❌ Subclasses with more restrictive behavior (e.g., shrinking preconditions)
- ❌ Using abstract classes when behaviors diverge significantly

---

## ✅ Integration So Far

- **SRP** → Each class has 1 reason to change
- **OCP** → New gateways added without modifying core
- **LSP** → Gateways substitute properly only where behavior is supported

---

**Next up:** Interface Segregation Principle (ISP) — to handle different notification channels in isolation (email, SMS, Slack).