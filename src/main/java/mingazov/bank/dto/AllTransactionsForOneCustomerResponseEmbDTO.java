package mingazov.bank.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import mingazov.bank.entities.Account;
import mingazov.bank.entities.Customer;
import mingazov.bank.entities.LogBalance;
import mingazov.bank.entities.OperationType;

import java.time.LocalDateTime;

@Getter
@Setter
public class AllTransactionsForOneCustomerResponseEmbDTO {
    private OperationType operationType;
    private Long amount;
    private Long fromAccountNumber;
    private Long toAccountNumber;
    private LocalDateTime timestamp;

    public AllTransactionsForOneCustomerResponseEmbDTO(LogBalance log) {
        this.operationType = log.getOperationType();
        this.amount = log.getAmount();
        this.fromAccountNumber = log.getFromAccountNumber();
        this.toAccountNumber = log.getToAccountNumber();
        this.timestamp = log.getTimestamp();
    }
}
