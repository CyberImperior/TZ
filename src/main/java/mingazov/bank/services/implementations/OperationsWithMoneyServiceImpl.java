package mingazov.bank.services.implementations;

import lombok.RequiredArgsConstructor;
import mingazov.bank.entities.Account;
import mingazov.bank.entities.Customer;
import mingazov.bank.entities.OperationType;
import mingazov.bank.exceptions.IncorrectNumberAccountException;
import mingazov.bank.exceptions.NotEnoughMoneyException;
import mingazov.bank.repositories.AccountRepository;
import mingazov.bank.services.interfaces.LogBalanceService;
import mingazov.bank.services.interfaces.OperationsWithMoneyService;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class OperationsWithMoneyServiceImpl implements OperationsWithMoneyService {
    private final AccountRepository accountRepository;
    private final LogBalanceService logBalanceService;

    public void transfer(Long from, Long to, Long amount, Customer customer) {
        var messageFrom = "Неверно указан ваш номер счета";
        var messageTo = "Неверно указан номер счета получателя";
        var accountFrom = findByAccountNumberOrIncorrectNumberAccountException(from, messageFrom);
        var accountTo = findByAccountNumberOrIncorrectNumberAccountException(to, messageTo);
        isBalanceNonNegative(accountFrom, amount);
        accountFrom.setBalance(accountFrom.getBalance() - amount);
        accountTo.setBalance(accountTo.getBalance() + amount);
        accountRepository.save(accountFrom);
        accountRepository.save(accountTo);
        logBalanceService.createTransaction(from, to, amount, customer, OperationType.TRANSFER);

    }
    public void withdraw(Long from, Long amount, Customer customer) {
        var message = "Неверно указан ваш номер счета";
        var accountFrom = findByAccountNumberOrIncorrectNumberAccountException(from, message);
        isBalanceNonNegative(accountFrom, amount);
        accountFrom.setBalance(accountFrom.getBalance() - amount);
        accountRepository.save(accountFrom);
        logBalanceService.createTransaction(from, null, amount, customer, OperationType.WITHDRAW);

    }
    public void replenishment(Long from, Long amount, Customer customer) {
        var message = "Неверно указан ваш номер счета";
        var accountFrom = findByAccountNumberOrIncorrectNumberAccountException(from, message);
        accountFrom.setBalance(accountFrom.getBalance() + amount);
        accountRepository.save(accountFrom);
        logBalanceService.createTransaction(from, null, amount, customer, OperationType.REPLENISHMENT);
    }
    public Account findByAccountNumberOrIncorrectNumberAccountException(Long accountNumber, String message) {
       return  accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new IncorrectNumberAccountException(message));
    }

    public void isBalanceNonNegative(Account accountFrom, Long amount) {
        if (accountFrom.getBalance() - amount < 0) {
            throw new NotEnoughMoneyException("На счете недостаточно средств");
        }
    }
}
