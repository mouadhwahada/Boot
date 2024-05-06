package tn.spring.pispring.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.spring.pispring.Entities.Payment;

import java.math.BigDecimal;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {

    @Query("SELECT SUM(p.amount) FROM Payment p")
    BigDecimal getTotalPaymentAmount();


}
