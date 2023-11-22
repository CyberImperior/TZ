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
@ExtendWith(MockitoExtension.class)
public class OperationsWithMoneyServiceTest {
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private LogBalanceService logBalanceService;
    @InjectMocks
    private OperationsWithMoneyServiceImpl operationsWithMoneyService;

    @Test
    public void testOperation() {

    }

    @Test
    public void testOperationIfIncorrectFromAccountNumber() {

    }
    @Test
    public void testOperationIfIncorrectToAccountNumber() {

    }
    @Test
    public void testOperationIfTransferAndNotEnoughMoney() {

    }
    @Test
    public void testOperationWithdrawAndNotEnoughMoney() {

    }
    @Test
    public void testFindByAccountNumber() {

    }

    public boolean operation(Long from, Long to, Long amount, Customer customer, OperationType type) {
        var accountFrom = accountRepository.findByAccountNumber(from)
                .orElseThrow(() -> new IncorrectNumberAccountException("Неверно указан ваш номер счета"));
        if (type == OperationType.TRANSFER) {
            var accountTo = accountRepository.findByAccountNumber(to)
                    .orElseThrow(() -> new IncorrectNumberAccountException("Неверно указан номер счета получателя"));
            if (accountFrom.getBalance() - amount < 0) {
                throw new NotEnoughMoneyException("На счете недостаточно средств");
            }
            accountFrom.setBalance(accountFrom.getBalance() - amount);
            accountTo.setBalance(accountTo.getBalance() + amount);

            accountRepository.save(accountTo);
        } else if (type == OperationType.WITHDRAW) {
            if (accountFrom.getBalance() - amount < 0) {
                throw new NotEnoughMoneyException("На счете недостаточно средств");
            }
            accountFrom.setBalance(accountFrom.getBalance() - amount);
        } else {
            accountFrom.setBalance(accountFrom.getBalance() + amount);
        }
        accountRepository.save(accountFrom);
        logBalanceService.createTransaction(from, to, amount, customer, type);
        return true;
    }
    public Optional<Account> findByAccountNumber(Long accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }
}
