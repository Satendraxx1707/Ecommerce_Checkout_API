package com.satendrait.ecommerce_checkout.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "address")
@Data
public class Address {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

   /* //  THIS METHOD WAS MISSING
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }*/
}