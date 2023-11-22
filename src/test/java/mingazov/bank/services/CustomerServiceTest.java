package mingazov.bank.services;

import mingazov.bank.dto.CustomerAuthenticateRequestDTO;
import mingazov.bank.entities.Customer;
import mingazov.bank.exceptions.IncorrectPinException;
import mingazov.bank.repositories.CustomerRepository;
import mingazov.bank.services.implementations.CustomerServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;
    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    public void testFindCustomerByUsername() {
        var customer = new Customer();
        customer.setUsername("Valya");

        when(customerRepository.findByUsername(customer.getUsername())).thenReturn(Optional.of(customer));

        Assertions.assertEquals(customerRepository.findByUsername(customer.getUsername()), Optional.of(customer));

    }
    @Test
    public void testIsValidPin() {
        var customer = new Customer();
        customer.setPin((short) 1111);

        assertTrue(customerService.isValidPin((short)1111, customer));
    }
    @Test
    public void testIsValidPinIfPinLengthMoreOrLessFour() {
        var customer = new Customer();
        customer.setPin((short) 1111);

        assertThrows(IncorrectPinException.class,
                () -> customerService.isValidPin((short) 111, customer));

    }
    @Test
    public void testCheckUsernameAndPin() {
        var dto = new CustomerAuthenticateRequestDTO();
        dto.setPin((short) 1111);
        dto.setUsername("Slava");

        var customer = new Customer();
        customer.setPin((short) 1111);

        when(customerRepository.findByUsername(dto.getUsername())).thenReturn(Optional.of(customer));
        assertEquals(customerService.checkUsernameAndPin(dto), customer);

    }
    @Test
    public void testCheckUsernameAndPinIfCustomerNotFound() {
        var dto = new CustomerAuthenticateRequestDTO();
        dto.setUsername("testUser");
        dto.setPin((short) 1111);

        var customer = new Customer();
        customer.setPin((short) 2222);

        when(customerRepository.findByUsername(dto.getUsername())).thenReturn(Optional.of(customer));


        assertThrows(IncorrectPinException.class,
                () -> customerService.checkUsernameAndPin(dto));
    }
    @Test
    public void testCheckUsernameAndPinIfIncorrectPin() {
        var dto = new CustomerAuthenticateRequestDTO();
        dto.setPin((short) 1111);
        dto.setUsername("Slava");

        var customer = new Customer();
        customer.setPin((short) 2222);

        when(customerRepository.findByUsername(dto.getUsername())).thenReturn(Optional.of(customer));

        assertThrows(IncorrectPinException.class,
                () -> customerService.checkUsernameAndPin(dto));

    }
}
