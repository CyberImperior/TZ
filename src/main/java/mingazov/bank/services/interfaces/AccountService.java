package mingazov.bank.services.interfaces;

import mingazov.bank.dto.CustomerAddAccountRequestDTO;
import mingazov.bank.entities.Account;
import mingazov.bank.entities.Customer;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    Optional<Account> findByAccountNumber(Long accountNumber);
    List<Account> findAccountsByCustomer_Id(Long id);
    boolean createAccount(Customer customer);
    public void addAccountsForCustomer(Customer customer, CustomerAddAccountRequestDTO dto);
}
