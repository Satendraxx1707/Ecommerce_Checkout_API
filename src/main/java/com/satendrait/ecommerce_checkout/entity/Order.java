package com.satendrait.ecommerce_checkout.entity;

import com.satendrait.ecommerce_checkout.PaymentStatus;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.util.ArrayList;
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

  @Enumerated(EnumType.STRING)
    @Column(name = "payment_status")
    private PaymentStatus paymentStatus;

    @Column(name = "razorpay_payment_id")
    private String razorpayPaymentId;

    @Column(name = "razorpay_order_id")
    private String razorpayOrderId;

  // # Mapping

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

  @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

  @ManyToOne
    @JoinColumn(name = "address_id")
    private Address shippingAddress;


    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setShippingAddress(Address address) {
        this.shippingAddress = address;
    }
}
