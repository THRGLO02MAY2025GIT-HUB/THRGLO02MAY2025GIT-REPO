package designpatterns.labs;

import designpatterns.structural.PayPalAPI;
import designpatterns.structural.PaymentProcessor;

public class PayPalAdapter implements PaymentProcessor {
    private designpatterns.structural.PayPalAPI payPalAPI;

    public PayPalAdapter(PayPalAPI payPalAPI) {
        this.payPalAPI = payPalAPI;
    }

    @Override
    public void processPayment(double amount) {
      payPalAPI.makePayment(amount);
    }
}
