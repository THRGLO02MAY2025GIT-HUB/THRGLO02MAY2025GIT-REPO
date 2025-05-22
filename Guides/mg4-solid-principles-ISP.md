# 🧩 Principle: Interface Segregation Principle (ISP)

> **“Clients should not be forced to depend on interfaces they do not use.”**

---

## ✅ 1. Context: FinTech – Notification Channels

Our payment system notifies users and operators via multiple channels:

- 📧 **Email** to customer
- 📱 **SMS** for mobile alerts
- 💬 **Slack** for internal support/ops team

If we lump all into one fat interface (e.g., `Notifier`), we risk forcing classes to implement methods they don’t need, violating ISP.

---

## ❌ 2. Violation – Fat Interface

```java
interface Notifier {
    void sendEmail(String address, String message);
    void sendSMS(String phoneNumber, String message);
    void sendSlack(String channel, String message);
}

// A notifier that only supports email
class EmailOnlyNotifier implements Notifier {
    public void sendEmail(String address, String message) {
        System.out.println("[Email] To: " + address + ", Msg: " + message);
    }

    public void sendSMS(String phoneNumber, String message) {
        throw new UnsupportedOperationException("SMS not supported");
    }

    public void sendSlack(String channel, String message) {
        throw new UnsupportedOperationException("Slack not supported");
    }
}
```

**🚨 What’s Wrong?**

- ❌ `EmailOnlyNotifier` is forced to implement unrelated methods
- ❌ Leads to runtime exceptions
- ❌ Violates SRP too — class now handles concerns it shouldn't

---

## ✅ 3. ISP-Compliant Redesign – Split Interfaces

```java
interface EmailNotifier {
    void sendEmail(String address, String message);
}

interface SMSNotifier {
    void sendSMS(String phoneNumber, String message);
}

interface SlackNotifier {
    void sendSlack(String channel, String message);
}
```

---

## 💡 4. Fine-Grained Implementations

```java
class BasicEmailNotifier implements EmailNotifier {
    public void sendEmail(String address, String message) {
        System.out.println("[Email] Sent to: " + address + " | Message: " + message);
    }
}

class TwilioSMSNotifier implements SMSNotifier {
    public void sendSMS(String phoneNumber, String message) {
        System.out.println("[SMS] To: " + phoneNumber + " | Message: " + message);
    }
}

class OpsSlackNotifier implements SlackNotifier {
    public void sendSlack(String channel, String message) {
        System.out.println("[Slack] #" + channel + " | " + message);
    }
}
```

---

## 🧑‍💻 5. Usage – Only What’s Needed

```java
class NotificationService {
    private final EmailNotifier email;
    private final SMSNotifier sms;

    public NotificationService(EmailNotifier email, SMSNotifier sms) {
        this.email = email;
        this.sms = sms;
    }

    public void notifyCustomer(String emailAddr, String phone, String msg) {
        email.sendEmail(emailAddr, msg);
        sms.sendSMS(phone, msg);
    }
}
```

Only the interfaces needed are implemented or passed — zero pollution.

---

## 📈 6. Benefits of ISP in FinTech Notification

| Benefit                | Explanation                                         |
|------------------------|-----------------------------------------------------|
| ✅ Clean contracts     | Each class does only what it's supposed to          |
| 🧪 Easier testing      | Isolated unit tests for each notifier               |
| 🔧 Higher flexibility  | Combine channels freely as needed                   |
| 🧼 Avoids side-effects | No risk of unexpected `UnsupportedOperationException`|

---

## 🧠 7. Best Practices

- Design interface per use case, not per hierarchy
- Never “stub out” methods you don’t need — it’s a design smell
- Prefer composition of interfaces in services (as above)
- Think capabilities, not catch-all interfaces

---

## 🚫 8. Anti-Patterns That Violate ISP

- Monolithic service interfaces like `Notifier`, `UserManager`, or `DataStore`
- DTOs or APIs where half the fields are null because clients don’t need them
- Adapter classes full of “unsupported” methods

---

## ✅ Integration Recap

| Principle | Outcome                                                        |
|-----------|----------------------------------------------------------------|
| SRP       | Each concern (logging, fraud check, gateway) is separated      |
| OCP       | New gateways added without touching orchestrator               |
| LSP       | Substitutable gateways ensure correctness (refunds)            |
| ISP       | Notification responsibilities are well-separated               |

---

**Next:** _DIP – Dependency Inversion Principle, where we eliminate direct coupling and inject abstractions instead._