package com.example.FlywayDemo2.Controller;
import com.example.FlywayDemo2.Model.Order;
import com.example.FlywayDemo2.Model.OrderItem;
import com.example.FlywayDemo2.Services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
  public class OrderController {


    private OrderService orderService;

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Order>> getOrdersByCustomerId(@PathVariable int customerId) {
        List<Order> orders = orderService.getOrderByCustomerId(customerId);
        return ResponseEntity.ok(orders);
    }

    @PostMapping
    public ResponseEntity<Order> placeOrder(
            @RequestParam int customerId,
            @RequestParam BigDecimal paymentAmount,
            @RequestBody List<OrderItem> orderItems) {

        Order order = new Order();
        order.setId(customerId);

        Order savedOrder = orderService.placeOrder(order, orderItems, paymentAmount);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }
}