package solid.lsp;

public class RefundService {
    public void issueRefund(RefundablePaymentProcessor refundablePaymentProcessor, double amount, String account) {
        refundablePaymentProcessor.refund(amount, account);
    }
}
