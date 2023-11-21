package mingazov.bank.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mingazov.bank.util.GenerateAccountNumber;


@Entity
@NoArgsConstructor
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long accountNumber = GenerateAccountNumber.get();
    private Long amountOfMoney = 0L;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
