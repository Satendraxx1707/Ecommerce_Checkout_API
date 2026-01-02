package com.satendrait.ecommerce_checkout.repository;



import com.satendrait.ecommerce_checkout.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {


    Order findByRazorpayOrderId(String razorpayOrderId);

    List<Order> findByCustomer_Id(Long customerId);
}
