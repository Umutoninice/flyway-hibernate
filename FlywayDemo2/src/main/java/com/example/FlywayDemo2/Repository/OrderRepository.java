package com.example.FlywayDemo2.Repository;

import com.example.FlywayDemo2.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderRepository extends JpaRepository<Order ,Integer> {
    List<Order>findByCustomerId(int customerId);

}
