package mingazov.bank.services.implementations;

import lombok.RequiredArgsConstructor;
import mingazov.bank.dto.CustomerAddAccountRequestDTO;
import mingazov.bank.entities.Account;
import mingazov.bank.entities.Customer;
import mingazov.bank.repositories.AccountRepository;
import mingazov.bank.services.interfaces.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    public Optional<Account> findByAccountNumber(Long accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }
    public List<Account> findAccountsByCustomer_Id(Long id) {
        return accountRepository.findAccountsByCustomer_Id(id);
    }
    public boolean createAccount(Customer customer) {
        var account = new Account();
        account.setCustomer(customer);
        accountRepository.save(account);
        return true;
    }

    public void addAccountsForCustomer(Customer customer, CustomerAddAccountRequestDTO dto) {
        for (int i = 0; i < dto.getAmountOfAccounts(); i++) {
            createAccount(customer);
        }
    }
}
