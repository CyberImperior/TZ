package mingazov.bank.services.interfaces;

import mingazov.bank.entities.Account;
import mingazov.bank.entities.Customer;


public interface OperationsWithMoneyService {
    void transfer(Long from, Long to, Long amount, Customer customer);
    void withdraw(Long from, Long amount, Customer customer);
    void replenishment(Long from, Long amount, Customer customer);
    Account findByAccountNumberOrIncorrectNumberAccountException(Long accountNumber, String message);

    void isBalanceNonNegative(Account accountFrom, Long amount);

}
