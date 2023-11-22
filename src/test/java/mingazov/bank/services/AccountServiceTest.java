package mingazov.bank.services;

import mingazov.bank.dto.CustomerAddAccountRequestDTO;
import mingazov.bank.entities.Account;
import mingazov.bank.entities.Customer;
import mingazov.bank.repositories.AccountRepository;
import mingazov.bank.services.implementations.AccountServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {
    @Mock
    private AccountRepository accountRepository;
    @InjectMocks
    private AccountServiceImpl accountService;

    @Test
    public void testFindAccountsByCustomer_Id() {
        Long customerId = 999L;
        var account1 = new Account();
        var account2 = new Account();

        when(accountRepository.findAccountsByCustomer_Id(customerId)).thenReturn(Arrays.asList(account1, account2));
        List<Account> accounts = accountRepository.findAccountsByCustomer_Id(customerId);

        assertEquals(account1, accounts.get(0));
        assertEquals(account2, accounts.get(1));
        assertEquals(accounts.size(), 2);

        verify(accountRepository, times(1)).findAccountsByCustomer_Id(customerId);
    }

    @Test
    public void testCreateAccount() {
        var customer = new Customer();

        assertTrue(accountService.createAccount(customer));
        verify(accountRepository, times(1)).save(any(Account.class));
    }
    @Test
    public void testAddAccountsForCustomer() {
        Customer customer = new Customer();
        CustomerAddAccountRequestDTO dto = new CustomerAddAccountRequestDTO();
        dto.setAmountOfAccounts(5);

        accountService.addAccountsForCustomer(customer, dto);

        verify(accountRepository, times(5)).save(any(Account.class));
    }

}
