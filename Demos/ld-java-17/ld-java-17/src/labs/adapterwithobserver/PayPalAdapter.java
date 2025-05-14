package labs.adapterwithobserver;

 class PayPalAdapter implements PaymentProcessor {
    private PayPalAPI payPalAPI;

    public PayPalAdapter(PayPalAPI payPalAPI) {
        this.payPalAPI = payPalAPI;
    }

    @Override
    public void processPayment(double amount) {
      payPalAPI.makePayment(amount);
    }
}
