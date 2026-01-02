package com.satendrait.ecommerce_checkout.service;


import com.satendrait.ecommerce_checkout.PaymentStatus;
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
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Transactional
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


    @Transactional
    @Override
    public PurchaseResponse saveOrder(PurchaseDTO purchase,
                                      String razorpayOrderId,
                                      String razorpayPaymentId) {

        // 1. Save Customer   --> Customer (reuse if exists)

        // corrected save() method
        Customer customer = customerRepository.findByEmail(purchase.getCustomer().getEmail())
                .orElseGet(() -> customerRepository.save(purchase.getCustomer()));


        // 2. Save Address
        Address address = purchase.getShippingAddress();
        address.setCustomer(customer);
        addressRepository.save(address);

        // 3. Save Order
        Order order = purchase.getOrder();
        order.setCustomer(customer);
        order.setShippingAddress(address);

        order.setPaymentStatus(PaymentStatus.COMPLETED); //corrected -PaymentStatus.COMPLETED-
        order.setRazorpayOrderId(razorpayOrderId);
        order.setRazorpayPaymentId(razorpayPaymentId);

        orderRepository.save(order);     // Save-method Used

        // 4. Save Order Items
        for (OrderItem item : purchase.getOrderItems()) {
            item.setOrder(order);
            //  orderItemRepository.save(item);   ->❌ Not wrong, but unnecessary

            //order.setOrderItems(purchase.getOrderItems());   // used this

            order.getOrderItems().add(item);      // ✅ add to owning side
        }
        // orderRepository.save(order);
        // Save ONLY parent (Hibernate handles children)


        customer.add(order);        // if mapped
        customerRepository.save(customer);



        System.out.println(">>> saveOrder called successfully <<<");

        return new PurchaseResponse(order.getId(), "Order Stored Successfully!");


    }


}


