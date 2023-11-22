package mingazov.bank.dto;

import lombok.Getter;
import lombok.Setter;
import mingazov.bank.entities.Account;

@Getter
@Setter
public class AccountsResponseEmbDTO {
    private Long accountNumber;
    private Long balance;
    public AccountsResponseEmbDTO(Account account) {
        this.accountNumber = account.getAccountNumber();
        this.balance = account.getBalance();
    }
}
