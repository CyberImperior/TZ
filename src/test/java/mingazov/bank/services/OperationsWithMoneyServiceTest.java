package mingazov.bank.services;

import mingazov.bank.entities.Account;
import mingazov.bank.entities.Customer;
import mingazov.bank.entities.OperationType;
import mingazov.bank.exceptions.IncorrectNumberAccountException;
import mingazov.bank.exceptions.NotEnoughMoneyException;
import mingazov.bank.repositories.AccountRepository;
import mingazov.bank.services.implementations.OperationsWithMoneyServiceImpl;
import mingazov.bank.services.interfaces.LogBalanceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OperationsWithMoneyServiceTest {
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private LogBalanceService logBalanceService;
    @InjectMocks
    private OperationsWithMoneyServiceImpl operationsWithMoneyService;

    @Test
    public void testTransfer() {
        Long from = 22222222222L;
        Long to = 111111111L;
        Long amount = 100L;
        var customer = new Customer();
        var accountFrom = new Account();
        accountFrom.setAccountNumber(from);
        accountFrom.setBalance(200L);
        var accountTo = new Account();
        accountTo.setAccountNumber(to);
        accountTo.setBalance(900L);

        when(accountRepository.findByAccountNumber(from)).thenReturn(Optional.of(accountFrom));
        when(accountRepository.findByAccountNumber(to)).thenReturn(Optional.of(accountTo));

        operationsWithMoneyService.transfer(from,to, amount, customer);

        assertEquals(100L, accountFrom.getBalance());
        assertEquals(1000L, accountTo.getBalance());

        verify(accountRepository, times(2)).save(any(Account.class));
        verify(logBalanceService, times(1)).createTransaction(from,to, amount, customer, OperationType.TRANSFER);
    }
    @Test
    public void withdraw() {
        Long from = 22222222222L;
        Long amount = 100L;
        Customer customer = new Customer();

        Account accountFrom = new Account();
        accountFrom.setBalance(1100L);

        when(accountRepository.findByAccountNumber(from)).thenReturn(Optional.of(accountFrom));

        operationsWithMoneyService.withdraw(from, amount, customer);
        assertEquals(1000L, accountFrom.getBalance());

        verify(accountRepository, times(1)).save(any(Account.class));
        verify(logBalanceService, times(1))
                .createTransaction(from, null, amount, customer, OperationType.WITHDRAW);
    }
    @Test
    public void replenishment() {
        Long from = 22222222222L;
        Long amount = 100L;
        Customer customer = new Customer();

        Account accountFrom = new Account();
        accountFrom.setBalance(900L);

        when(accountRepository.findByAccountNumber(from)).thenReturn(Optional.of(accountFrom));

        operationsWithMoneyService.replenishment(from, amount, customer);
        assertEquals(1000L, accountFrom.getBalance());
        verify(accountRepository, times(1)).save(any(Account.class));
        verify(logBalanceService, times(1))
                .createTransaction(from, null, amount, customer, OperationType.REPLENISHMENT);
    }
    @Test
    public void testWithdrawIfNotEnoughMoney() {
        Long from = 22222222222L;
        Long amount = 1000L;
        Customer customer = new Customer();

        Account accountFrom = new Account();
        accountFrom.setBalance(900L);

        when(accountRepository.findByAccountNumber(from))
                .thenReturn(java.util.Optional.of(accountFrom));

        assertThrows(NotEnoughMoneyException.class,
                () -> operationsWithMoneyService.withdraw(from, amount, customer));

        verify(accountRepository, never()).save(any());
        verify(logBalanceService, never())
                .createTransaction(any(), any(), any(), any(), any());
    }

    @Test
    public void testFindByAccountNumberOrIncorrectNumberAccountExceptionIfException() {
        Long accountNumber = 22222222222L;
        String message = "Неверно указан номер счета";

        when(accountRepository.findByAccountNumber(accountNumber))
                .thenReturn(java.util.Optional.empty());

        assertThrows(IncorrectNumberAccountException.class,
                () -> operationsWithMoneyService.findByAccountNumberOrIncorrectNumberAccountException(accountNumber, message));
    }
}
