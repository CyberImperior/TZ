package mingazov.bank.services.interfaces;

import mingazov.bank.entities.Account;
import mingazov.bank.entities.Customer;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    public Optional<Account> findByAccountNumber(Long accountNumber);
    public List<Account> findAccountsByCustomer_Id(Long id);
    public boolean createAccount(Customer customer);
}
