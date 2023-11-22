package mingazov.bank.services;

import mingazov.bank.dto.CustomerAuthenticateRequestDTO;
import mingazov.bank.entities.Customer;
import mingazov.bank.exceptions.CustomerNotExistsException;
import mingazov.bank.exceptions.IncorrectPinException;
import mingazov.bank.repositories.CustomerRepository;
import mingazov.bank.services.implementations.CustomerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;
    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    public void testFindCustomerByUsername() {

    }
    @Test
    public void testIsValidPin() {

    }
    @Test
    public void testIsValidPinIfPinLengthMoreOrLessFour() {

    }
    @Test
    public void testCheckUsernameAndPin() {

    }
    @Test
    public void testCheckUsernameAndPinIfCustomerNotFound() {

    }
    @Test
    public void testCheckUsernameAndPinIfIncorrectPin() {

    }
    public Optional<Customer> findCustomerByUsername(String username) {
        return  customerRepository.findByUsername(username);
    }
    public boolean isValidPin(Short requestPin, Customer customer) {
        if (String.valueOf(requestPin).length() != 4)
            throw new IncorrectPinException("Пин-код должен состоять из 4 цифр");
        return requestPin.equals(customer.getPin());
    }

    public Customer checkUsernameAndPin(CustomerAuthenticateRequestDTO dto) {
        var customer = findCustomerByUsername(dto.getUsername())
                .orElseThrow(() -> new CustomerNotExistsException("Пользователь не найден"));
        if (!isValidPin(dto.getPin(), customer))
            throw new IncorrectPinException("Неверно введен пин-код");
        return customer;
    }
}
