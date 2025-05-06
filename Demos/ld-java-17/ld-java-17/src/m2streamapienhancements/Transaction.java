package m2streamapienhancements;

import java.math.BigDecimal;

// Common domain class
class Transaction {
    private String id;
    private BigDecimal amount;

    public Transaction(String id, BigDecimal amount) {
        this.id = id;
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Transaction {id=" + id + ", amount=" + amount + "}";
    }
}

class TransactionSummary {
    private final double average;
    private final double total;

    public TransactionSummary(double average, double total) {
        this.average = average;
        this.total = total;
    }

    public double getAverage() {
        return average;
    }

    public double getTotal() {
        return total;
    }

    @Override
    public String toString() {
       return "TransactionSummary {average=" + average + ", total=" + total + "}";
    }
}
