package org.vinn.openECommerce.api.order.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.vinn.openECommerce.api.order.util.OrderStatus;

@Getter
@Setter
public class ChangeOrderStatusRequest {

    @NotNull
    private OrderStatus status;
}
