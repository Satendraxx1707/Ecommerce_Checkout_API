package com.satendrait.ecommerce_checkout.service;


import com.satendrait.ecommerce_checkout.dto.PurchaseDTO;
import com.satendrait.ecommerce_checkout.dto.PurchaseResponse;

import com.satendrait.ecommerce_checkout.entity.Address;
import com.satendrait.ecommerce_checkout.entity.Customer;
import com.satendrait.ecommerce_checkout.entity.Order;
import com.satendrait.ecommerce_checkout.entity.OrderItem;
import com.satendrait.ecommerce_checkout.repository.AddressRepository;
import com.satendrait.ecommerce_checkout.repository.CustomerRepository;
import com.satendrait.ecommerce_checkout.repository.OrderItemRepository;
import com.satendrait.ecommerce_checkout.repository.OrderRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;




    @Override
    public PurchaseResponse saveOrder(PurchaseDTO purchase,
                                      String razorpayOrderId,
                                      String razorpayPaymentId) {

        // 1. Save Customer
        Customer customer = purchase.getCustomer();
        customerRepository.save(customer);

        // 2. Save Address
        Address address = purchase.getShippingAddress();
        address.setCustomer(customer);
        addressRepository.save(address);

        // 3. Save Order
        Order order = purchase.getOrder();
        order.setCustomer(customer);
        order.setShippingAddress(address);

        order.setPaymentStatus("PAID");
        order.setRazorpayOrderId(razorpayOrderId);
        order.setRazorpayPaymentId(razorpayPaymentId);

        orderRepository.save(order);

        // 4. Save Order Items
        for (OrderItem item : purchase.getOrderItems()) {
            item.setOrder(order);
            orderItemRepository.save(item);
        }

        return new PurchaseResponse(order.getId(), "Order Stored Successfully!");

    }

}