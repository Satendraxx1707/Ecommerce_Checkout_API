package com.satendrait.ecommerce_checkout.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order {
  // added later
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int totalQuantity;
    private BigDecimal totalPrice;
    // Added later
    @Column(name = "payment_status")
    private String paymentStatus;

    @Column(name = "razorpay_payment_id")
    private String razorpayPaymentId;

    @Column(name = "razorpay_order_id")
    private String razorpayOrderId;


    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address shippingAddress;


    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setShippingAddress(Address address) {
        this.shippingAddress = address;
    }
}
