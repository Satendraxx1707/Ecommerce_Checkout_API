package com.satendrait.ecommerce_checkout.dto;

import lombok.Data;

@Data
public class PaymentCallbackDTO {
    private String razorpayPaymentId;
    private String razorpayOrderId;
    private String razorpaySignature;
    private PurchaseDTO purchase;
}
