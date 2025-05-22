# SOLID Principle: Dependency Inversion Principle (DIP) in FinTech Payment Gateway

Let’s complete the SOLID journey with the final principle: **Dependency Inversion Principle (DIP)**, applied to our FinTech Payment Gateway system.

---

## 🧩 Principle: Dependency Inversion Principle (DIP)

> **"High-level modules should not depend on low-level modules. Both should depend on abstractions."**  
> **"Abstractions should not depend on details. Details should depend on abstractions."**

---

## 1. Context: FinTech – Payment Report Generation

Suppose your system generates daily payment reports. Initially, it writes them directly to an SQL database. Later, you want to switch to Amazon S3 or Google Cloud Storage. You shouldn't have to rewrite your reporting logic just to change the storage backend.

---

## 2. Violation – Tight Coupling to Low-Level Class

```java
class SQLReportStorage {
    public void store(String reportContent) {
        System.out.println("[SQL] Stored report: " + reportContent);
    }
}

class HardCodedReportGenerator {
    public void generateReport() {
        SQLReportStorage storage = new SQLReportStorage();  // ❌ Concrete dependency
        storage.store("Daily Payment Report");
    }
}
```

**🚨 What’s Wrong?**

- `HardCodedReportGenerator` directly depends on SQL storage.
- Cannot swap storage backend (S3, NoSQL, file) without changing `generateReport()`.
- Violates both OCP (Open/Closed Principle) and DIP.

---

## 3. DIP-Compliant Design – Use Abstractions

**Define an abstraction:**

```java
interface ReportStorage {
    void store(String reportContent);
}
```

**Implementations:**

```java
class SQLStorage implements ReportStorage {
    public void store(String reportContent) {
        System.out.println("[SQL] Stored report: " + reportContent);
    }
}

class S3Storage implements ReportStorage {
    public void store(String reportContent) {
        System.out.println("[S3] Uploaded report: " + reportContent);
    }
}
```

**High-Level Module:**

```java
class DIPCompliantReportGenerator {
    private final ReportStorage storage;

    // Dependency is injected via constructor
    public DIPCompliantReportGenerator(ReportStorage storage) {
        this.storage = storage;
    }

    public void generateReport() {
        String content = "Daily Payment Report";
        storage.store(content);
    }
}
```

---

## 4. Main/Composition Root

```java
public class DIPDemo {
    public static void main(String[] args) {
        ReportStorage sqlStorage = new SQLStorage();     // or new S3Storage();
        DIPCompliantReportGenerator generator = new DIPCompliantReportGenerator(sqlStorage);

        generator.generateReport();  // Can easily switch to other storage
    }
}
```

---

## 5. Benefits of DIP in FinTech

| Benefit                | Explanation                                              |
|------------------------|---------------------------------------------------------|
| ✅ Pluggable architecture | Swap backend (SQL, S3, Kafka) without touching core logic |
| 🧪 Testable logic         | Inject mock storages during unit testing              |
| 🛡️ Decoupled layers       | Business logic isn’t tied to infrastructure           |
| ⚙️ Configurable runtime   | Compose dependencies externally (Spring, DI container)|

---

## 6. Best Practices

- Always code to interfaces, not implementations.
- Use constructor injection over setters to guarantee required dependencies.
- Centralize wiring in a composition root (e.g., Spring `@Configuration` or `main()`).
- In unit tests, use mock/stub implementations to isolate logic.

---

## 7. Anti-Patterns That Violate DIP

- Using `new` keyword inside business logic classes.
- Service classes that call database clients directly.
- Hardcoded logger/notification/email classes in critical workflows.

---

## ✅ Final Integration Recap

| SOLID Principle | Applied in FinTech Gateway                  | Outcome                                         |
|-----------------|---------------------------------------------|-------------------------------------------------|
| SRP             | Handler, Logger, Notifier, FraudChecker separated | Each class has 1 reason to change               |
| OCP             | Add new gateways without touching core logic | Extend via interfaces                           |
| LSP             | Refundable vs Non-refundable gateways        | Proper substitution with no runtime failure     |
| ISP             | Split notifier interfaces by channel         | Avoids bloated contracts                        |
| DIP             | Inject storage, processors, loggers via abstractions | Fully decoupled, testable, extensible system    |
