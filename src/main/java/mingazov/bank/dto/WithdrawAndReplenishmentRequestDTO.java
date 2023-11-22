package mingazov.bank.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WithdrawAndReplenishmentRequestDTO extends CustomerAuthenticateRequestDTO {
    private Long accountNumber;
    private Long amountOfOperation;
}
