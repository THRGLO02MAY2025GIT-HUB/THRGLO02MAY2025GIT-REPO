package labs.adapterwithobserver;

//Simulated Payment API
 class PayPalAPI {
    public void makePayment(double amount) {
        System.out.println("Processing payment of $" + amount + " with PayPal.");
    }
}
