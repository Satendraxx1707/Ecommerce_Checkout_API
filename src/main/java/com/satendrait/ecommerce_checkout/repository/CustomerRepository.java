package com.satendrait.ecommerce_checkout.repository;




import com.satendrait.ecommerce_checkout.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {}


