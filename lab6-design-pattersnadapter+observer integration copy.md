# Lab Exercise: Integrating Adapter and Observer Patterns
### Duration : 1 hour

In this lab, you will integrate the **Adapter Pattern** with the **Observer Pattern** to build a payment system that notifies different users on their mobile and web-site when their payments are processed. The Adapter Pattern unifies multiple payment gateways (e.g., PayPal, Stripe, Square) under a common interface, while the Observer Pattern notifies users about transaction statuses.

---

## Problem Context

Imagine building a FinTech system where:
- Users need to be notified when their payments are successfully processed on thier mobile and web-site as notifications
- Multiple payment gateways (PayPal, Stripe, Square) must be integrated to process payments.
- Notifications about transaction statuses (successful or failed) are sent using the Observer Pattern.
- The Adapter Pattern wraps different payment gateways under a common interface for easy integration.

---

## Lab Objectives

By the end of this lab, you will:
1. Understand the **Observer Pattern** and its role in notifying users about payment statuses.
2. Understand the **Adapter Pattern** and its role in unifying multiple payment gateways under a common interface.
3. Design and implement a system that combines both patterns to process payments and notify users.

---

## Lab Tasks

### Task 1: Define the Unified Payment Processor Interface
- Create an interface that defines a common method for processing payments.

### Task 2: Implement Payment Gateway Classes
- Create classes for PayPal, Stripe, and Square payment gateways with their respective APIs.

### Task 3: Create Adapters for Payment Gateways
- Implement adapter classes for each payment gateway to conform to the unified payment processor interface.

### Task 4: Implement the Observer Pattern
- Define an observer interface for receiving payment status updates.
- Create a concrete observer class (e.g., `User`) that represents users who will receive notifications.
- Implement a subject class (e.g., `PaymentStatusSubject`) that manages observers and notifies them of payment status changes.

### Task 5: Build the Payment Service
- Create a `PaymentService` class that integrates the payment processor and the payment status subject.
- Ensure the service processes payments and notifies observers about the payment status.

### Task 6: Integrate and Test the System
- Instantiate payment gateways, adapters, and the payment service.
- Add observers (users) to the payment status subject.
- Process payments through different payment gateways and observe the notifications.

---

## Expected Outcome

After completing the lab, you should have a working payment processing system that:
- Uses the Adapter Pattern to integrate multiple payment gateways under a common interface.
- Uses the Observer Pattern to notify users about payment statuses.
- Demonstrates extensibility by allowing the addition of new payment gateways and observers without modifying existing code.

---

### Expected Output
```
Making payment through PayPal:  
Processing payment of $120.0 with PayPal.  
User Mr.Passion notified on mobile. Payment status: Payment of $120.0 processed successfully.  
User Mr.Passion notified on website. Payment status: Payment of $120.0 processed successfully.  

Making payment through Stripe:  
Charging $200.0 with Stripe.  
User Ms.Grace notified on mobile. Payment status: Payment of $200.0 processed successfully.  
User Ms.Grace notified on website. Payment status: Payment of $200.0 processed successfully.  

Making payment through Square:  
Processing payment of $150.0 with Square.  
User Mr.Passion notified on mobile. Payment status: Payment of $150.0 processed successfully.  
User Mr.Passion notified on website. Payment status: Payment of $150.0 processed successfully.  

```
## Reflection Questions

1. How does the Adapter Pattern simplify the integration of multiple payment gateways?
2. What are the benefits of using the Observer Pattern for notifying users about payment statuses?
3. How would you extend the system to support a new payment gateway?
4. How does this design promote scalability and maintainability in a real-world application?

---



## Conclusion

This lab exercise demonstrates how the **Adapter Pattern** and **Observer Pattern** can be combined to build a scalable and maintainable payment processing system. The design ensures flexibility, extensibility, and centralized control, making it ideal for robust FinTech applications.



## Lab Exercise: Implementing Custom Annotations for JSON Serialization
### Duration: 1 hour

In this lab, you will implement a custom annotation-based system for converting Java objects into JSON strings. You will use reflection to process annotations and dynamically generate JSON output. This exercise will help you understand how annotations and reflection can be used to build extensible and reusable frameworks.

---

## Problem Context

Imagine building a lightweight JSON serialization library where:
- Classes must be annotated to indicate they are serializable.
- Fields can be annotated to specify custom JSON keys.
- Methods can be annotated to initialize or preprocess data before serialization.
- Reflection is used to dynamically inspect and process annotations at runtime.

---

## Lab Objectives

By the end of this lab, you will:
1. Understand how to define and use custom annotations in Java.
2. Learn how to use reflection to inspect and process annotations.
3. Implement a JSON serialization system that uses annotations for flexibility and customization.

---

## Lab Tasks

### Task 1: Define Custom Annotations
- Create the following annotations:
    - `@JsonSerializable`: Marks a class as serializable.
    - `@JsonElement`: Marks a field to be included in the JSON output and allows specifying a custom key.
    - `@Init`: Marks a method to be invoked before serialization.

### Task 2: Implement the `ModelToJsonConverter` Class
- Implement the following methods:
    - `checkIfSerializable(Object object)`: Ensures the object is annotated with `@JsonSerializable`.
    - `initializeModel(Object object)`: Invokes methods annotated with `@Init`.
    - `getJsonString(Object object)`: Generates a JSON string by inspecting fields annotated with `@JsonElement`.

### Task 3: Create a Test Model Class
- Create a class (e.g., `Customer`) annotated with `@JsonSerializable`.
- Add fields annotated with `@JsonElement` and specify custom keys where necessary.
- Add a method annotated with `@Init` to preprocess data.

### Task 4: Test the Serialization Process
- Instantiate the `Customer` class and populate its fields.
- Use the `ModelToJsonConverter` to serialize the object into a JSON string.
- Verify the output matches the expected JSON format.

---

## Expected Outcome

After completing the lab, you should have a working JSON serialization system that:
- Uses annotations to control serialization behavior.
- Dynamically processes annotations using reflection.
- Produces JSON output in the expected format.

---

### Expected Output
```java
>>>>>>>>>> Serialization Check!
>>>>>>>>>> Initializing Model with proper Case!
{"firstName":"John", "lastName":"Rambo", "successfulTransactionFormatted":"50"}
```

---

## Reflection Questions

1. How do annotations improve the flexibility and readability of the serialization system?
2. What are the advantages of using reflection for processing annotations?
3. How would you extend the system to support nested objects or collections?
4. What are the potential performance implications of using reflection in a real-world application?

---

## Conclusion

This lab exercise demonstrates how custom annotations and reflection can be used to build a flexible and extensible JSON serialization system. By leveraging annotations, you can decouple serialization logic from the model classes, making the system easier to maintain and extend.