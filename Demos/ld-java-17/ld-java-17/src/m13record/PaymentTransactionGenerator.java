package m13record;

// Create a basic record for a payment transaction
// Declare the components in the record
// The record keyword is used to create a record class
// The first line is the record header, followed by the record components
record SimplePaymentTransaction(
        // The below are components
        String transactionId,
        double amount,
        String currency
) {

}

// class for comparison
class SimplePaymentTransactionClass {
    String transactionId;
    double amount;
    String currency;
}

public class PaymentTransactionGenerator {
    public static void main(String[] args) {
        SimplePaymentTransaction payment = new SimplePaymentTransaction("TX001", 99.9, "USD");
        // Display details to check toString() method which is automatically implemented
        System.out.println(payment);
        SimplePaymentTransactionClass paymentClass = new SimplePaymentTransactionClass();
        System.out.println(paymentClass);

        // Access individual fields using automatic getters
        // Print the details of the various components in the SimplePaymentTransaction record.
        System.out.println("ID: " + payment.transactionId());
        System.out.println("Amount: " + payment.amount());
        System.out.println("Currency: " + payment.currency());
    }
}
