module com.banking.app {
    requires com.banking.api;
    // Declaring a service dependency
    // This module expects at runtime, there will be some implementation of the BankingService interface
    // Enables service loading via Service.Loader.load(Banking.class)
    // Critical : This will only work if some other module (like com.banking.core) provides the implementation.
    uses com.banking.api.service.BankingService;
}