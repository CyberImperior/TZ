package mingazov.bank.services.implementations;

import lombok.RequiredArgsConstructor;
import mingazov.bank.entities.Account;
import mingazov.bank.entities.Customer;
import mingazov.bank.exceptions.CustomerAlreadyExistsException;
import mingazov.bank.exceptions.IncorrectPinException;
import mingazov.bank.repositories.AccountRepository;
import mingazov.bank.repositories.CustomerRepository;
import mingazov.bank.services.interfaces.RegistrationService;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;
    public boolean createCustomerIfUsernameNotExist(Customer customer) {
        if (customerRepository.findByUsername(customer.getUsername()).isPresent())
            throw new CustomerAlreadyExistsException("Клиент уже существует");
        if (String.valueOf(customer.getPin()).length() != 4)
            throw new  IncorrectPinException("Пин-код должен состоять из 4 цифр");
        var account = new Account();
        account.setCustomer(customer);

        customerRepository.save(customer);
        accountRepository.save(account);
        return true;
    }
}
