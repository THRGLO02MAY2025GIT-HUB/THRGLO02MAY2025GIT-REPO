package m14sealedclasses;

sealed interface DigitalPayment permits CreditCardPayment, DebitCardPayment{
    double getAmount();

    String getCurrency();

    boolean process();
}


sealed class CreditCardPayment implements DigitalPayment permits InternationalCreditCardPayment {
    private final double amount;
    private final String currency;
    final String cardNumber;

    public CreditCardPayment(double amount, String currency, String cardNumber) {
        this.amount = amount;
        this.currency = currency;
        this.cardNumber = cardNumber;
    }

    @Override
    public double getAmount() {
        return amount;
    }

    @Override
    public String getCurrency() {
        return currency;
    }

    @Override
    public boolean process() {
        return true;
    }
}

sealed class DebitCardPayment implements DigitalPayment {
    private final double amount;
    private final String currency;
    private final String cardNumber;

    public DebitCardPayment(double amount, String currency, String cardNumber) {
        this.amount = amount;
        this.currency = currency;
        this.cardNumber = cardNumber;
    }

    @Override
    public double getAmount() {
        return amount;
    }

    @Override
    public String getCurrency() {
        return currency;
    }

    @Override
    public boolean process() {
        return true;
    }
}

//class CashPayment implements DigitalPayment {
//    private final double amount;
//    private final String currency;
//
//
//    public CashPayment(double amount, String currency) {
//        this.amount = amount;
//        this.currency = currency;
//
//    }
//
//    @Override
//    public double getAmount() {
//        return amount;
//    }
//
//    @Override
//    public String getCurrency() {
//        return currency;
//    }
//
//    @Override
//    public boolean process() {
//        return true;
//    }
//}

public class PaymentProcessor {
    public static void main(String[] args) {

    }
}


class DigitalPaymentClass {
    double getAmount() {
        return 0;
    }

    ;

    String getCurrency() {
        return "INR";
    }

    ;

    boolean process() {
        return true;
    }
}


class CreditCardPaymentClass extends DigitalPaymentClass {
    private final double amount;
    private final String currency;
    private final String cardNumber;

    public CreditCardPaymentClass(double amount, String currency, String cardNumber) {
        this.amount = amount;
        this.currency = currency;
        this.cardNumber = cardNumber;
    }

    @Override
    public double getAmount() {
        return amount;
    }

    @Override
    public String getCurrency() {
        return currency;
    }

    @Override
    public boolean process() {
        return true;
    }
}


class DebitCardPaymentClass extends DigitalPaymentClass {
    private final double amount;
    private final String currency;
    private final String cardNumber;

    public DebitCardPaymentClass(double amount, String currency, String cardNumber) {
        this.amount = amount;
        this.currency = currency;
        this.cardNumber = cardNumber;
    }

    @Override
    public double getAmount() {
        return amount;
    }

    @Override
    public String getCurrency() {
        return currency;
    }

    @Override
    public boolean process() {
        return true;
    }
}

class CashPaymentClass extends DigitalPaymentClass {
    private final double amount;
    private final String currency;
    private final String cardNumber;

    public CashPaymentClass(double amount, String currency, String cardNumber) {
        this.amount = amount;
        this.currency = currency;
        this.cardNumber = cardNumber;
    }

    @Override
    public double getAmount() {
        return amount;
    }

    @Override
    public String getCurrency() {
        return currency;
    }

    @Override
    public boolean process() {
        return true;
    }
}

//class CashPaymentTryingToIndirect extends CreditCardPayment {
//
//    public CashPaymentTryingToIndirect(double amount, String currency, String cardNumber) {
//        super(amount, currency, cardNumber);
//    }
//}


//    Sealed class must have subclasses
sealed class InternationalCreditCardPayment extends CreditCardPayment {

    public InternationalCreditCardPayment(double amount, String currency, String cardNumber) {
        super(amount, currency, cardNumber);
    }
}

final class HDFCInternationalCreditCardPayment extends InternationalCreditCardPayment {

    public HDFCInternationalCreditCardPayment(double amount, String currency, String cardNumber) {
        super(amount, currency, cardNumber);
    }
}



//non-sealed class InternationalCreditCardPayment extends CreditCardPayment {
//
//    public InternationalCreditCardPayment(double amount, String currency, String cardNumber) {
//        super(amount, currency, cardNumber);
//    }
//}
//
// class HDFCInternationalCreditCardPayment extends InternationalCreditCardPayment {
//
//    public HDFCInternationalCreditCardPayment(double amount, String currency, String cardNumber) {
//        super(amount, currency, cardNumber);
//    }
//}


final class InternationalDebitCardPayment extends DebitCardPayment {

    public InternationalDebitCardPayment(double amount, String currency, String cardNumber) {
        super(amount, currency, cardNumber);
    }
}