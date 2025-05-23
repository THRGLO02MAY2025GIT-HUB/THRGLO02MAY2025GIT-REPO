// Declares the module name
// This must match the folder name in the source tree and typically the Java package root.
module com.banking.core {
    // This module depends on com.banking.api
    // It import all the exported packages from com.banking.api
    // Without this you cannot use interfaces like BankingService or models like Account
    requires com.banking.api;
    // Service provider declaration
    // This module provides an implementation of the BankingService interface
    // from com.banking.api.service, and that the implementation is BankingServiceImpl
    // from com.banking.core.service
    // Enables service loading via Service.Loader.load(Banking.class)
    // This is a clean, runtime-discoverable form of dependency injection.
    provides com.banking.api.service.BankingService
            with com.banking.core.service.BankingServiceImpl;
    exports com.banking.core.service;
}