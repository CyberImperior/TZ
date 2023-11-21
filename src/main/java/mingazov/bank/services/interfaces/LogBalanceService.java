package mingazov.bank.services.interfaces;

import mingazov.bank.entities.Customer;
import mingazov.bank.entities.LogBalance;
import mingazov.bank.entities.OperationType;

import java.time.LocalDateTime;
import java.util.List;

public interface LogBalanceService {
    boolean createTransaction(Long from, Long to, Long amount, Customer customer, OperationType type);
    List<LogBalance> findAccountsByCustomer_Id(Long id);
}
