package mingazov.bank.services.interfaces;

import mingazov.bank.dto.CustomerAuthenticateRequestDTO;
import mingazov.bank.entities.Customer;

import java.util.Optional;

public interface CustomerService {
    Optional<Customer> findCustomerByUsername(String username);
    boolean isValidPin(Short requestPin, Customer customer);
    Customer checkUsernameAndPin(CustomerAuthenticateRequestDTO dto);
}
