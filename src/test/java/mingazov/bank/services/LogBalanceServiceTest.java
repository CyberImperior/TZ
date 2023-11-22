package mingazov.bank.services;


import mingazov.bank.entities.Customer;
import mingazov.bank.entities.LogBalance;
import mingazov.bank.entities.OperationType;
import mingazov.bank.repositories.LogBalanceRepository;
import mingazov.bank.services.implementations.LogBalanceServiceImpl;
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
public class LogBalanceServiceTest {
    @Mock
    private LogBalanceRepository logBalanceRepository;
    @InjectMocks
    private LogBalanceServiceImpl logBalanceService;

    @Test
    public void testCreateTransaction() {
        var customer = new Customer();
        OperationType type = OperationType.TRANSFER;
        Long from = 4122664556325L;
        Long to = 5684465464666L;
        Long amount = 12000L;

        assertTrue(logBalanceService.createTransaction(from, to, amount, customer, type));

        verify(logBalanceRepository, times(1)).save(any(LogBalance.class));

    }
    @Test
    public void testFindAccountsByCustomer_Id() {
        Long customerId = 999L;
        var logBalance1 = new LogBalance();
        var logBalance2 = new LogBalance();

        when(logBalanceRepository.findLogBalancesByCustomer_Id(customerId)).thenReturn(Arrays.asList(logBalance1, logBalance2));
        List<LogBalance> logBalances = logBalanceRepository.findLogBalancesByCustomer_Id(customerId);

        assertEquals(logBalance1, logBalances.get(0));
        assertEquals(logBalance2, logBalances.get(1));
        assertEquals(2, logBalances.size());

        verify(logBalanceRepository, times(1)).findLogBalancesByCustomer_Id(customerId);
    }

}
