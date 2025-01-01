package org.vinn.openECommerce.api.orderItem.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderItemDTO {
    private String productName;
    private int quantity;
    private BigDecimal priceAtPurchase;
    private BigDecimal totalPrice;
}
