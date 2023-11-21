package mingazov.bank.services.implementations;

import lombok.RequiredArgsConstructor;
import mingazov.bank.entities.Customer;
import mingazov.bank.repositories.CustomerRepository;
import mingazov.bank.services.interfaces.CustomerService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    public Optional<Customer> findCustomerByUsername(String username) {
        return  customerRepository.findByUsername(username);
    }
    public boolean isValidPin(Short requestPin, Customer customer) {
        return requestPin.equals(customer.getPin());
    }
}
