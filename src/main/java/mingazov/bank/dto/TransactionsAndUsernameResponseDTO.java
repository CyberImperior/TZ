package mingazov.bank.dto;

import lombok.Getter;
import lombok.Setter;
import mingazov.bank.entities.Customer;
import mingazov.bank.entities.LogBalance;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class TransactionsAndUsernameResponseDTO {
    private String username;
    private List<TransactionsResponseEmbDTO> logs;
    public TransactionsAndUsernameResponseDTO(Customer customer, List<LogBalance> logs) {
        this.username = customer.getUsername();
        this.logs = logs.stream().map(TransactionsResponseEmbDTO::new).collect(Collectors.toList());
    }
}
