package mingazov.bank.services.interfaces;

import mingazov.bank.entities.Account;
import mingazov.bank.entities.Customer;
import mingazov.bank.entities.OperationType;

import java.util.Optional;

public interface OperationsWithMoneyService {
    boolean operation(Long from, Long to, Long amount, Customer customer, OperationType type);
    Optional<Account> findByAccountNumber(Long accountNumber);
}
