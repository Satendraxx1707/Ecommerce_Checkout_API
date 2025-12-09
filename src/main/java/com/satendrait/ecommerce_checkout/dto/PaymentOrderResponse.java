package com.satendrait.ecommerce_checkout.dto;



import lombok.Data;

@Data 
public class PaymentOrderResponse {

    private String razorpayOrderId;
    private int amount;
    private String currency;

}
