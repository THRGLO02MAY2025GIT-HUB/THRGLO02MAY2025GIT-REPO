package solid.lsp;

public class LSPMain {
    public static void main(String[] args) {
        RefundService refundService = new RefundService();
        RefundablePaymentProcessor stripe = new StripeProcessor();
        refundService.issueRefund(stripe, 150, "acct-001");

        PaymentProcessor crypto = new CryptoProcessor();
        crypto.process(500,"wallet-xyz");
        // The PaymentProcessor does not allow issue refund and does not break the code......
        // This won't compile - and that's good!
//        refundService.issueRefund(crypto, 150, "acct-001");
    }
}
