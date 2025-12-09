package com.satendrait.ecommerce_checkout.service;



import com.satendrait.ecommerce_checkout.dto.PurchaseDTO;
import com.satendrait.ecommerce_checkout.dto.PurchaseResponse;

public interface CheckoutService {

    PurchaseResponse placeOrder(PurchaseDTO purchase);
}
