package mingazov.bank.dto;

import lombok.Getter;
import lombok.Setter;
import mingazov.bank.entities.Account;
import mingazov.bank.entities.Customer;
import mingazov.bank.util.GenerateAccountNumber;

import java.util.Set;

@Getter
@Setter
public class CustomerCreateRequestDTO extends CustomerAuthenticateRequestDTO {

    public static Customer convert(CustomerCreateRequestDTO customerDTO) {
        var customer = new Customer();
        customer.setUsername(customerDTO.getUsername());
        customer.setPin(customerDTO.getPin());

        var account = new Account();
        account.setCustomer(customer);

        return customer;
    }
    private Set<Account> accounts;


}
