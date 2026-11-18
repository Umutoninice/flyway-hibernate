package com.example.FlywayDemo2.Repository;

import com.example.FlywayDemo2.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Customer findByEmail(String email);
    boolean existsByEmail(String email);
}
