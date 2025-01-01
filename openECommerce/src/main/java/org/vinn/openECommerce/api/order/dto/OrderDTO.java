package org.vinn.openECommerce.api.order.dto;

import lombok.Getter;
import lombok.Setter;
import org.vinn.openECommerce.api.order.util.OrderStatus;
import org.vinn.openECommerce.api.orderItem.dto.OrderItemDTO;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderDTO {
    private Long id;
    private OrderStatus status;
    private LocalDateTime orderDate;
    private List<OrderItemDTO> orderItems;
}
