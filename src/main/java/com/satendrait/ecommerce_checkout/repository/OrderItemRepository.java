package com.satendrait.ecommerce_checkout.repository;

import com.satendrait.ecommerce_checkout.entity.Order;
import com.satendrait.ecommerce_checkout.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {


    List<OrderItem> findByOrderCustomerId(Long customerId);
}
