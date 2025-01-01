package org.vinn.openECommerce.api.order.service;

import org.vinn.openECommerce.api.order.dto.AddOrderRequest;
import org.vinn.openECommerce.api.order.dto.ChangeOrderStatusRequest;
import org.vinn.openECommerce.api.order.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    OrderDTO createOrder(AddOrderRequest addOrderRequest);
    OrderDTO changeOrderStatus(Long id, ChangeOrderStatusRequest request);
    OrderDTO cancelOrder(Long id);
    OrderDTO getOrder(Long id);
    List<OrderDTO> getOrders();
    void deleteOrder(Long id);
}
