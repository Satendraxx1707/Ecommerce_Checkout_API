package com.satendrait.ecommerce_checkout.repository;




import com.satendrait.ecommerce_checkout.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByEmail(String email);
}


