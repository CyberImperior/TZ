package mingazov.bank.services.interfaces;

import mingazov.bank.dto.CustomerAddAccountRequestDTO;
import mingazov.bank.entities.Account;
import mingazov.bank.entities.Customer;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    List<Account> findAccountsByCustomer_Id(Long id);
    boolean createAccount(Customer customer);
    void addAccountsForCustomer(Customer customer, CustomerAddAccountRequestDTO dto);
}
