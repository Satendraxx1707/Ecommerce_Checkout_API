package com.satendrait.ecommerce_checkout.controller;



import com.satendrait.ecommerce_checkout.dto.PurchaseDTO;
import com.satendrait.ecommerce_checkout.dto.PurchaseResponse;
import com.satendrait.ecommerce_checkout.service.CheckoutService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/checkout")
@CrossOrigin("http://localhost:4200")
public class CheckoutController {

    private final CheckoutService checkoutService;

    // Constructor Injection
    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }


    // http://localhost:9099/api/checkout/purchase  (Working)
    @PostMapping("/purchase")
    public PurchaseResponse placeOrder(@RequestBody PurchaseDTO purchase) {


        // Call service layer to process order
        PurchaseResponse response =  checkoutService.placeOrder(purchase);

        // Return response back to frontend / Postman
        return response;
    }
}
