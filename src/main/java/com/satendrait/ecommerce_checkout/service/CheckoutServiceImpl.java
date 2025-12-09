package com.satendrait.ecommerce_checkout.service;



import com.satendrait.ecommerce_checkout.dto.PurchaseDTO;
import com.satendrait.ecommerce_checkout.dto.PurchaseResponse;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    @Override
    public PurchaseResponse placeOrder(PurchaseDTO purchase) {

        // TODO: Next step - yahi pe DB save, Razorpay order create karenge

        // For now: randomly tracking number + fake Razorpay orderId generateed
        String trackingNumber = UUID.randomUUID().toString();
        String razorpayOrderId = "razorpay_" + UUID.randomUUID().toString();

        System.out.println("===== NEW PURCHASE RECEIVED =====");
        System.out.println(purchase);

        return new PurchaseResponse(0L, razorpayOrderId);
    }
}
