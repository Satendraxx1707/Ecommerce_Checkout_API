package com.satendrait.ecommerce_checkout.dto;



import lombok.Data;

@Data
public class PurchaseResponse {


    private String orderTrackingNumber;

    // constructor
    public PurchaseResponse(Long id, String orderTrackingNumber) {
        this.orderTrackingNumber = orderTrackingNumber;
    }

    public String getOrderTrackingNumber() {
        return orderTrackingNumber;
    }
}


    /*
    private Long orderId;
    private String message;

    public PurchaseResponse(Long orderId, String message) {
        this.orderId = orderId;
        this.message = message;
    }
}
*/