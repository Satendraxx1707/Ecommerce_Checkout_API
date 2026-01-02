package com.satendrait.ecommerce_checkout.controller;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.satendrait.ecommerce_checkout.config.RazorpayConfig;
import com.satendrait.ecommerce_checkout.dto.PaymentCallbackDTO;
import com.satendrait.ecommerce_checkout.dto.PaymentOrderResponse;
import com.satendrait.ecommerce_checkout.dto.PurchaseDTO;

import com.satendrait.ecommerce_checkout.dto.PurchaseResponse;
import com.satendrait.ecommerce_checkout.service.OrderService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/payment")


//  http://localhost:9099/api/payment    -->  for frontend connection
// start
public class PaymentController {

    @Autowired
    private RazorpayConfig config;

    @Autowired
    private OrderService orderService;

    // STEP 1: Create Razorpay Order -------------------->
        @PostMapping("/create-order")
    public PaymentOrderResponse createOrder(@RequestBody PurchaseDTO purchaseDTO) throws Exception {

        RazorpayClient client = new RazorpayClient(config.getKeyId(), config.getKeySecret());

        int amount = purchaseDTO.getOrder()
                .getTotalPrice()
                .multiply(new BigDecimal(100))
                .intValue();


        JSONObject options = new JSONObject();
        options.put("amount", amount);
        options.put("currency", "INR");
        options.put("receipt", "txn_" + System.currentTimeMillis());

        Order order = client.orders.create(options);

         // PaymentOrderResponse-------------------->
        PaymentOrderResponse response = new PaymentOrderResponse();
        response.setRazorpayOrderId(order.get("id"));
        response.setAmount(amount);
        response.setCurrency("INR");

        return response;
    }

    // STEP 2: Verify Payment + SAVE ORDER INTO DB

    @PostMapping("/verify-payment")
    public PurchaseResponse verifyPayment(@RequestBody PaymentCallbackDTO callback) throws Exception {

        String data = callback.getRazorpayOrderId() + "|" + callback.getRazorpayPaymentId();
        String generatedSignature = calculateHMAC(data, config.getKeySecret());


 // ADD later for check

        System.out.println("Generated: " + generatedSignature);
        System.out.println("Received : " + callback.getRazorpaySignature());




        if (!generatedSignature.equals(callback.getRazorpaySignature())) {
            throw new RuntimeException("Payment Verification Failed!");
        }

        // Payment verified → Save order into database
        PurchaseResponse response = orderService.saveOrder(
                callback.getPurchase(),
                callback.getRazorpayOrderId(),
                callback.getRazorpayPaymentId()
        );

        return response;
    }



    private String calculateHMAC(String data, String secret) throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
        sha256_HMAC.init(secretKey);

        byte[] hash = sha256_HMAC.doFinal(data.getBytes());

        // Convert to Hex String manually
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            hexString.append(String.format("%02x", b));
        }

        return hexString.toString();
    }



}
