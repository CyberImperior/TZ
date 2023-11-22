package mingazov.bank.repositories;

import mingazov.bank.entities.LogBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogBalanceRepository extends JpaRepository<LogBalance, Long> {
    List<LogBalance> findLogBalancesByCustomer_Id(Long id);
}
