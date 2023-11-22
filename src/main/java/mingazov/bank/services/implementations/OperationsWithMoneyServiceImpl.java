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

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OperationsWithMoneyServiceImpl implements OperationsWithMoneyService {
    private final AccountRepository accountRepository;
    private final LogBalanceService logBalanceService;

    public boolean operation(Long from, Long to, Long amount, Customer customer, OperationType type) {
        var accountFrom = accountRepository.findByAccountNumber(from)
                .orElseThrow(() -> new IncorrectNumberAccountException("Неверно указан ваш номер счета"));
        if (type == OperationType.TRANSFER) {
            var accountTo = accountRepository.findByAccountNumber(to)
                    .orElseThrow(() -> new IncorrectNumberAccountException("Неверно указан номер счета получателя"));
            if (accountFrom.getAmountOfMoney() - amount < 0) {
                throw new NotEnoughMoneyException("На счете недостаточно средств");
            }
            accountFrom.setAmountOfMoney(accountFrom.getAmountOfMoney() - amount);
            accountTo.setAmountOfMoney(accountTo.getAmountOfMoney() + amount);

            accountRepository.save(accountTo);
        } else if (type == OperationType.WITHDRAW) {
            if (accountFrom.getAmountOfMoney() - amount < 0) {
                throw new NotEnoughMoneyException("На счете недостаточно средств");
            }
            accountFrom.setAmountOfMoney(accountFrom.getAmountOfMoney() - amount);
        } else {
            accountFrom.setAmountOfMoney(accountFrom.getAmountOfMoney() + amount);
        }
        accountRepository.save(accountFrom);
        logBalanceService.createTransaction(from, to, amount, customer, type);
        return true;
    }
    public Optional<Account> findByAccountNumber(Long accountNumber) {
       return accountRepository.findByAccountNumber(accountNumber);
    }


}
