package mingazov.bank.services.implementations;

import lombok.RequiredArgsConstructor;
import mingazov.bank.entities.Account;
import mingazov.bank.entities.Customer;
import mingazov.bank.repositories.AccountRepository;
import mingazov.bank.repositories.CustomerRepository;
import mingazov.bank.services.interfaces.RegistrationService;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;

    // todo Потом поправить на эксепшн

    // todo Попробовать метод вызвать через метод, чтобы было меньше кода
    public boolean createCustomerIfUsernameNotExist(Customer customer) {
        if (customerRepository.findByUsername(customer.getUsername()).isPresent())
            return false;
        var account = new Account();
        account.setCustomer(customer);

        customerRepository.save(customer);
        accountRepository.save(account);
        return true;
    }
}
