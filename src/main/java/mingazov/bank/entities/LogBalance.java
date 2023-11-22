package mingazov.bank.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class LogBalance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private OperationType operationType;
    private Long amount;
    private Long fromAccountNumber;
    private Long toAccountNumber;
    private LocalDateTime timestamp;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @PrePersist
    public void prePersist() {
        this.timestamp = LocalDateTime.now();
    }

}
