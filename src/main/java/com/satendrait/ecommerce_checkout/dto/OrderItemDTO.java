package com.satendrait.ecommerce_checkout.dto;



import lombok.Data;

@Data
public class OrderItemDTO {

    private String imageUrl;
    private double unitPrice;
    private int quantity;
    private Long productId;
}
