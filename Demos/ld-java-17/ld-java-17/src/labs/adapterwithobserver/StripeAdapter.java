package labs.adapterwithobserver;



 class StripeAdapter implements PaymentProcessor {
    private StripeAPI stripeAPI;
    public StripeAdapter(StripeAPI stripeAPI) {
        this.stripeAPI = stripeAPI;
    }

    @Override
    public void processPayment(double amount) {
        stripeAPI.chargeAmount(amount);
    }
}
