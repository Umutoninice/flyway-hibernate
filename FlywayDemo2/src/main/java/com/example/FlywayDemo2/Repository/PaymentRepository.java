package com.example.FlywayDemo2.Repository;

import com.example.FlywayDemo2.Model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    Optional<Payment>findByOrderId(int orderId);
}
