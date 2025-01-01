package org.vinn.openECommerce.api.order.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AddOrderRequest {

    @NotNull(message = "Order items must not be null")
    private List<OrderItemRequest> orderItems;
}
