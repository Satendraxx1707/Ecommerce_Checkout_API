package com.satendrait.ecommerce_checkout.repository;



import com.satendrait.ecommerce_checkout.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {


    Order findByRazorpayOrderId(String razorpayOrderId);

}
