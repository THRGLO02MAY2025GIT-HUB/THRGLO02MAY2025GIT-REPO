package labs.adapterwithobserver;

 class StripeAPI {
    public void chargeAmount(double amount) {
        System.out.println("Charging $" + amount + " using Stripe.");
    }
}
