package mingazov.bank.dto;


import lombok.Getter;
import lombok.Setter;
import mingazov.bank.entities.Account;
import mingazov.bank.entities.Customer;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class CustomerAccountsResponseDTO {
    private String username;
    private List<AccountsResponseEmbDTO> accounts;
    public CustomerAccountsResponseDTO(Customer customer, List<Account> accounts) {
        this.username = customer.getUsername();
        this.accounts = accounts.stream().map(AccountsResponseEmbDTO::new).collect(Collectors.toList());
    }

}
