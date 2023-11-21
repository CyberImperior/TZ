package mingazov.bank.dto;

import lombok.Getter;
import lombok.Setter;
import mingazov.bank.entities.Account;
import mingazov.bank.entities.Customer;

import java.util.Set;

@Getter
@Setter
public class CustomerAddAccountRequestDTO extends CustomerAuthenticateRequestDTO {
        private Integer amountOfAccounts;
}
