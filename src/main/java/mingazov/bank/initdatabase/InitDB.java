package mingazov.bank.initdatabase;

import lombok.RequiredArgsConstructor;
import mingazov.bank.entities.Account;
import mingazov.bank.entities.Customer;
import mingazov.bank.repositories.AccountRepository;
import mingazov.bank.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class InitDB implements CommandLineRunner {
    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;

    @Override
    public void run(String... args){
        if (customerRepository.findById(1L).isPresent())
            return;

        var customer1 = new Customer();
        customer1.setUsername("Denis");
        customer1.setPin((short)2343);
        customerRepository.save(customer1);

        var customer2 = new Customer();
        customer2.setUsername("Ivan");
        customer2.setPin((short)1313);
        customerRepository.save(customer2);

        var account1 = new Account();
        account1.setCustomer(customer1);
        account1.setBalance(10000L);
        account1.setAccountNumber(1292937931691L);
        accountRepository.save(account1);

        var account2 = new Account();
        account2.setCustomer(customer1);
        account2.setBalance(100000000L);
        account2.setAccountNumber(1292937931692L);
        accountRepository.save(account2);

        var account3 = new Account();
        account3.setCustomer(customer2);
        account3.setBalance(100L);
        account3.setAccountNumber(1292937931693L);
        accountRepository.save(account3);

        var account4 = new Account();
        account4.setCustomer(customer2);
        account4.setAccountNumber(1292937931694L);
        accountRepository.save(account4);
    }
}
