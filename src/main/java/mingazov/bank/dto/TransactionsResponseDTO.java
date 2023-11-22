package mingazov.bank.dto;

import lombok.Getter;
import lombok.Setter;
import mingazov.bank.entities.Customer;
import mingazov.bank.entities.LogBalance;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class TransactionsResponseDTO {
    private String username;
    private List<AllTransactionsForOneCustomerResponseEmbDTO> logs;
    public TransactionsResponseDTO(Customer customer, List<LogBalance> logs) {
        this.username = customer.getUsername();
        this.logs = logs.stream().map(AllTransactionsForOneCustomerResponseEmbDTO::new).collect(Collectors.toList());
    }
}
