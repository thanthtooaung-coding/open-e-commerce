package org.vinn.openECommerce.api.order.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vinn.openECommerce.api.order.dto.AddOrderRequest;
import org.vinn.openECommerce.api.order.dto.ChangeOrderStatusRequest;
import org.vinn.openECommerce.api.order.dto.OrderDTO;
import org.vinn.openECommerce.api.order.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("${api.base.path}/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody @Valid AddOrderRequest addOrderRequest) {
        OrderDTO orderDTO = orderService.createOrder(addOrderRequest);
        return ResponseEntity.ok(orderDTO);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<OrderDTO> changeOrderStatus(
            @PathVariable Long id,
            @RequestBody @Valid ChangeOrderStatusRequest changeOrderStatusRequest) {
        OrderDTO updatedOrder = orderService.changeOrderStatus(id, changeOrderStatusRequest);
        return ResponseEntity.ok(updatedOrder);
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<OrderDTO> cancelOrder(@PathVariable Long id) {
        OrderDTO cancelledOrder = orderService.cancelOrder(id);
        return ResponseEntity.ok(cancelledOrder);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrder(@PathVariable Long id) {
        OrderDTO orderDTO = orderService.getOrder(id);
        return ResponseEntity.ok(orderDTO);
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getOrders() {
        return ResponseEntity.ok(orderService.getOrders());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
