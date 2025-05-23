module com.banking.core {
    requires com.banking.api;
    provides com.banking.api.service.BankingService
            with com.banking.core.service.BankingServiceImpl;
    exports com.banking.core.service;
}