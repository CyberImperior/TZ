package mingazov.bank.services.implementations;

import lombok.RequiredArgsConstructor;
import mingazov.bank.dto.CustomerAuthenticateRequestDTO;
import mingazov.bank.entities.Customer;
import mingazov.bank.exceptions.CustomerNotExistsException;
import mingazov.bank.exceptions.IncorrectPinException;
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

    public Customer checkUsernameAndPin(CustomerAuthenticateRequestDTO dto) {
        var customer = findCustomerByUsername(dto.getUsername())
                .orElseThrow(() -> new CustomerNotExistsException("Пользователь не найден"));
//        if (customer == null)
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Пользователь с именем "
//                    + accountRequestDTO.getUsername() + " не найден");
        if (!isValidPin(dto.getPin(), customer))
            throw new IncorrectPinException("Неверно введен пин-код");
        return customer;
    }
}
