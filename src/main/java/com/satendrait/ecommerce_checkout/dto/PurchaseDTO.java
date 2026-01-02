package com.satendrait.ecommerce_checkout.dto;




import com.satendrait.ecommerce_checkout.entity.Address;
import com.satendrait.ecommerce_checkout.entity.Customer;
import com.satendrait.ecommerce_checkout.entity.Order;
import com.satendrait.ecommerce_checkout.entity.OrderItem;
import lombok.Data;
import java.util.List;

@Data
public class PurchaseDTO {

   /* private CustomerDTO customer;
    private AddressDTO shippingAddress;ab
    private AddressDTO billingAddress;  // added
    private OrderDTO order;
    private List<OrderItemDTO> orderItems;
}
*/
 // for nowits okay In larning phase , later on need to change

    
    private Customer customer;
    private Address shippingAddress;
    private Order order;
    private List<OrderItem> orderItems;


}