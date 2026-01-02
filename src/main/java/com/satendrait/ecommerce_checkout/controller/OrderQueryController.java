package com.satendrait.ecommerce_checkout.controller;



import com.satendrait.ecommerce_checkout.entity.Order;

import com.satendrait.ecommerce_checkout.repository.OrderRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class OrderQueryController {

    private final OrderRepository orderRepository;

    public OrderQueryController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // ✅ Dashboard ke liye orders fetch
    // http://localhost:9099/api/orders/16



    @GetMapping("/orders/{customerId}")
    public List<Order> getOrdersByCustomer(@PathVariable Long customerId) {
        return orderRepository.findByCustomer_Id(customerId);
    }
}
