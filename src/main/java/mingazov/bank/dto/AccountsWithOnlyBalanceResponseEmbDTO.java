package mingazov.bank.dto;

import lombok.Getter;
import lombok.Setter;
import mingazov.bank.entities.Account;

@Getter
@Setter
public class AccountsWithOnlyBalanceResponseEmbDTO {

    private Long accountNumber;
    //todo переименовать все в balance
    private Long balance;
    public AccountsWithOnlyBalanceResponseEmbDTO(Account account) {
        this.accountNumber = account.getAccountNumber();
        this.balance = account.getAmountOfMoney();
    }
}
