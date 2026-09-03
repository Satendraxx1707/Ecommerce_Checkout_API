package com.satendrait.ecommerce_checkout.service;



import com.satendrait.ecommerce_checkout.dto.PurchaseDTO;
import com.satendrait.ecommerce_checkout.dto.PurchaseResponse;
import com.satendrait.ecommerce_checkout.entity.Order;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class CheckoutServiceImpl implements CheckoutService {


    private final OrderService orderService;

    public CheckoutServiceImpl(OrderService orderService) {
        this.orderService = orderService;



}

    @Override
    public PurchaseResponse placeOrder(PurchaseDTO purchase) {




       //26
        // 1️⃣ Order entity create + save
        Order order = new Order();

        System.out.println(">>> CheckoutService.placeOrder CALLED <<<");

        //  Next step - yahi pe DB save, Razorpay order create karenge

        // For now: randomly tracking number + fake Razorpay orderId generateed


        // TEMP: Fake Razorpay IDs (Postman testing ke liye)


        // orderTrackingNumber generate

        String razorpayOrderId = "razorpay_" + UUID.randomUUID();
        String razorpayPaymentId = "pay_" + UUID.randomUUID();






        // old :
       // String trackingNumber = UUID.randomUUID().toString();
      //  String razorpayOrderId = "razorpay_" + UUID.randomUUID().toString();

        //  ACTUAL DB SAVE HAPPENS HERE

        //return new PurchaseResponse(0L, razorpayOrderId);



  // new updated
        return orderService.saveOrder(
                purchase,
                razorpayOrderId,
                razorpayPaymentId
        );
    }
}
