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

## Reflection Questions

1. How does the Adapter Pattern simplify the integration of multiple payment gateways?
2. What are the benefits of using the Observer Pattern for notifying users about payment statuses?
3. How would you extend the system to support a new payment gateway?
4. How does this design promote scalability and maintainability in a real-world application?

---



## Conclusion

This lab exercise demonstrates how the **Adapter Pattern** and **Observer Pattern** can be combined to build a scalable and maintainable payment processing system. The design ensures flexibility, extensibility, and centralized control, making it ideal for robust FinTech applications.
