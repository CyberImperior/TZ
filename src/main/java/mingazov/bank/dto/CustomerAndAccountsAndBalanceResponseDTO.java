package mingazov.bank.dto;


import lombok.Getter;
import lombok.Setter;
import mingazov.bank.entities.Account;
import mingazov.bank.entities.Customer;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class CustomerAndAccountsAndBalanceResponseDTO {
    private String username;
    private List<AccountsWithOnlyBalanceResponseEmbDTO> accounts;
    public CustomerAndAccountsAndBalanceResponseDTO(Customer customer, List<Account> accounts) {
        this.username = customer.getUsername();
        this.accounts = accounts.stream().map(AccountsWithOnlyBalanceResponseEmbDTO::new).collect(Collectors.toList());
    }

}
