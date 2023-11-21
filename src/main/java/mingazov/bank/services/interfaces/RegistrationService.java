package mingazov.bank.services.interfaces;

import mingazov.bank.entities.Customer;

public interface RegistrationService {
    boolean createCustomerIfUsernameNotExist(Customer customer);
}
