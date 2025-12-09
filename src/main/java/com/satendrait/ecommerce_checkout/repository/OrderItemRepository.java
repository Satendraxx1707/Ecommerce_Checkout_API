package com.satendrait.ecommerce_checkout.repository;

import com.satendrait.ecommerce_checkout.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {}
