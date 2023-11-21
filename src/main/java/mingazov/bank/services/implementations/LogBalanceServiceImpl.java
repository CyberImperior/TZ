package mingazov.bank.services.implementations;

import lombok.RequiredArgsConstructor;
import mingazov.bank.entities.Customer;
import mingazov.bank.entities.LogBalance;
import mingazov.bank.entities.OperationType;
import mingazov.bank.repositories.LogBalanceRepository;
import mingazov.bank.services.interfaces.LogBalanceService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LogBalanceServiceImpl implements LogBalanceService {
    private final LogBalanceRepository logBalanceRepository;

    public boolean createTransaction(Long from, Long to, Long amount, Customer customer, OperationType type) {
        LogBalance logBalance = new LogBalance();
        logBalance.setAmount(amount);
        logBalance.setFromAccountNumber(from);
        logBalance.setTimestamp(LocalDateTime.now());
        logBalance.setOperationType(type);
        logBalance.setCustomer(customer);
        if (type == OperationType.TRANSFER) {
            logBalance.setToAccountNumber(to);
        } else {
            logBalance.setToAccountNumber(null);
        }
        logBalanceRepository.save(logBalance);
        return true;
    }

    public List<LogBalance> findAccountsByCustomer_Id(Long id) {
        return logBalanceRepository.findLogBalancesByCustomer_Id(id);
    }
}
