package mingazov.bank.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerAddAccountRequestDTO extends CustomerAuthenticateRequestDTO {
        private Integer amountOfAccounts;
}
