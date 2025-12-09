package com.satendrait.ecommerce_checkout.service;

import com.satendrait.ecommerce_checkout.dto.PurchaseDTO;
import com.satendrait.ecommerce_checkout.dto.PurchaseResponse;


public interface OrderService {

    // OLD method for compatibility
   // PurchaseResponse saveOrder(PurchaseDTO purchaseDTO);

    // NEW method for Razorpay
    PurchaseResponse saveOrder(PurchaseDTO purchaseDTO,
                               String razorpayOrderId,
                               String razorpayPaymentId);

}
