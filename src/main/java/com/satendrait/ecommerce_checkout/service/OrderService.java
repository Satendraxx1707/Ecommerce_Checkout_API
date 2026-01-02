package com.satendrait.ecommerce_checkout.service;

import com.satendrait.ecommerce_checkout.dto.PurchaseDTO;
import com.satendrait.ecommerce_checkout.dto.PurchaseResponse;


public interface OrderService {

    // OLD method for compatibility
    // PurchaseResponse saveOrder(PurchaseDTO purchaseDTO);

    // NEW method for Razorpay
   public  PurchaseResponse saveOrder(PurchaseDTO purchaseDTO,
                               String razorpayOrderId,
                               String razorpayPaymentId) ;


        //  Check -2 Later
      //  System.out.println(">>> SAVE ORDER METHOD CALLED <<<");


}