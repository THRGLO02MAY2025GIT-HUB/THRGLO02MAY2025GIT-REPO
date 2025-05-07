package m3collectionfactorymethods;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionFactoryMethodsDemo {
    // A list of transaction statuses would be PENDING, COMPLETED, FAILED, etc.
    // A set of Payment methods would be CREDIT_CARD, DEBIT_CARD, PAYPAL, etc.
    // A default method of Payment wouuld CASH.
    // A map of Currency Exchange rates would be USD to INR, EUR to INR, etc.

    //Static factory methods
    private static final List<String> EMPTY_STATUSES = List.of();
    private static final List<String> TRANSACTION_STATUSES = List.of("PENDING","COMPLETED","FAILED","ABORTED");
    private static final Set<String> PAYMENT_METHODS = Set.of("CREDIT_CARD","DEBIT_CARD","BANK_TRANSFER","CRYPTO","PAYPAL");
    private static final Set<String> DEFAULT_METHOD = Set.of("CASH");

    private static final Map<String, Double> CURRENCY_EXCHANGE_RATES = Map.of(
            "USD_EUR", 0.9,
            "USD_GBP", 0.7,
            "USD_JPY", 150.5,
            "USD_INR", 75.0
    );
}

// Lab : Explore your existing code-base in your current project at your organization and find areas where your can apply these immutable collections and collection factory methods. (15 minutes)


