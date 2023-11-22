package mingazov.bank.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferRequestDTO extends WithdrawAndReplenishmentRequestDTO {
    private Long accountNumberTo;
}
