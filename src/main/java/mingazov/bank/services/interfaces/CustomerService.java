package mingazov.bank.services.interfaces;

import mingazov.bank.entities.Customer;
import mingazov.bank.repositories.CustomerRepository;

import java.util.Optional;

public interface CustomerService {
    Optional<Customer> findCustomerByUsername(String username);
    boolean isValidPin(Short requestPin, Customer customer);
}
