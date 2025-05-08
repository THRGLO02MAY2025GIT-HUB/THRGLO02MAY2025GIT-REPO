package m4privatemethodsininterfaces;

import java.math.BigDecimal;

public class Transaction {
    private final BigDecimal amount;
    private final String currency;
    private final String description;

    public Transaction(BigDecimal amount, String currency, String description) {
        this.amount = amount;
        this.currency = currency;
        this.description = description;
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getDescription() {
        return String.format("%s, %s, (%s)", amount, currency, description);
    }
}
