package designpatterns.labs;

class PaymentService {
    private PaymentProcessor paymentProcessor;
    private PaymentStatusSubject paymentStatusSubject;

    public PaymentService(PaymentProcessor paymentProcessor, PaymentStatusSubject paymentStatusSubject) {
        this.paymentProcessor = paymentProcessor;
        this.paymentStatusSubject = paymentStatusSubject;
    }

    // Process payment and notify users about the status
    public void processPayment(double amount) {
        // Process request using the appropriate gateway
        paymentProcessor.processPayment(amount);

        // Update the payment status and notify observers
        paymentStatusSubject.setStatus("Payment of $" + amount + "processed successfully");
    }
}
