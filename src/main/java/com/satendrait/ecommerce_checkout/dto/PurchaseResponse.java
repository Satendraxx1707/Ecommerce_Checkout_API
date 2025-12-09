package com.satendrait.ecommerce_checkout.dto;



import lombok.Data;

@Data
public class PurchaseResponse {

    private Long orderId;
    private String message;

    public PurchaseResponse(Long orderId, String message) {
        this.orderId = orderId;
        this.message = message;
    }
}
