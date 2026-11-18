package com.example.FlywayDemo2.Services;

import com.example.FlywayDemo2.Model.*;
import com.example.FlywayDemo2.Repository.CustomerRepository;
import com.example.FlywayDemo2.Repository.OrderRepository;
import com.example.FlywayDemo2.Repository.PaymentRepository;
import com.example.FlywayDemo2.Repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
@Service
public class OrderService {
    private OrderRepository orderRepository;
    private CustomerRepository customerRepository;
    private ProductRepository productRepository;
    private PaymentRepository paymentRepository;

    public List<Order> getOrderByCustomerId(int customerId) {

        return orderRepository.findByCustomerId(customerId);
    }

    public Order placeOrder(Order order, List<OrderItem> orderItems, BigDecimal paymentAmount) {

        Customer customer = customerRepository.findById(order.getId())
                .orElseThrow(() -> new RuntimeException("Customer with ID " + order.getId() + " not found!"));

        order.setStatus("PENDING");
        Order savedOrder = orderRepository.save(order);
        BigDecimal calculatedTotal = BigDecimal.ZERO;
        for (OrderItem item : orderItems) {
            Product product = productRepository.findById(item.getId())
                    .orElseThrow(() -> new RuntimeException("Product with ID " + item.getId() + " not found!"));
            BigDecimal itemTotal = product.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
            calculatedTotal = calculatedTotal.add(itemTotal);


            if (paymentAmount.compareTo(calculatedTotal) < 0) {
                throw new RuntimeException("Payment amount (" + paymentAmount + ") is less than total order cost (" + calculatedTotal + ")!");
            }

            Payment payment = new Payment();
            payment.setId(savedOrder.getId());
            payment.setAmount(paymentAmount);
            payment.setPaymentStatus(PaymentStatus.valueOf("SUCCESS"));
            paymentRepository.save(payment);

            savedOrder.setStatus("COMPLETED");
        }
        return orderRepository.save(savedOrder);

    }
};






