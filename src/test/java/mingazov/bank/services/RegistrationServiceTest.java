package mingazov.bank.services;

import mingazov.bank.entities.Account;
import mingazov.bank.entities.Customer;
import mingazov.bank.exceptions.CustomerAlreadyExistsException;
import mingazov.bank.exceptions.IncorrectPinException;
import mingazov.bank.repositories.AccountRepository;
import mingazov.bank.repositories.CustomerRepository;
import mingazov.bank.services.implementations.RegistrationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class RegistrationServiceTest {
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private  AccountRepository accountRepository;
    @InjectMocks
    private RegistrationServiceImpl registrationService;

    @Test
    public void testCreateCustomerIfUsernameNotExist() {
        var customer = new Customer();
        customer.setPin((short) 1355);
        customer.setUsername("Ivan");

        Mockito.when(customerRepository.findByUsername(customer.getUsername())).thenReturn(Optional.empty());

        assertTrue(registrationService.createCustomerIfUsernameNotExist(customer));
        verify(customerRepository, times(1)).findByUsername(customer.getUsername());
        verify(customerRepository, times(1)).save(customer);
        verify(accountRepository, times(1)).save(any(Account.class));
    }

    @Test
    public void testCreateCustomerIfUsernameAlreadyExist() {
        var customer = new Customer();
        customer.setPin((short) 1355);
        customer.setUsername("Ivan");

        Mockito.when(customerRepository.findByUsername(customer.getUsername()))
                .thenReturn(Optional.of(customer));

        assertThrows(CustomerAlreadyExistsException.class, () ->
                registrationService.createCustomerIfUsernameNotExist(customer));
        verify(customerRepository, times(1)).findByUsername(customer.getUsername());
        verify(customerRepository, never()).save(customer);
        verify(accountRepository, never()).save(any(Account.class));
    }


    @Test
    public void testCreateCustomerIfPinMoreAndLessFour() {
        var customer1 = new Customer();
        customer1.setPin((short) 13255);
        customer1.setUsername("Ivan");

        var customer2 = new Customer();
        customer2.setPin((short) 135);
        customer2.setUsername("Ivan");

        assertThrows(IncorrectPinException.class, () -> registrationService.createCustomerIfUsernameNotExist(customer1));
        assertThrows(IncorrectPinException.class, () -> registrationService.createCustomerIfUsernameNotExist(customer2));

        verify(customerRepository, times(2)).findByUsername(customer1.getUsername());
        verify(customerRepository, never()).save(customer1);
        verify(accountRepository, never()).save(any(Account.class));
    }

}
