package org.vinn.openECommerce.api.order.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vinn.openECommerce.api.order.dto.AddOrderRequest;
import org.vinn.openECommerce.api.order.dto.ChangeOrderStatusRequest;
import org.vinn.openECommerce.api.order.dto.OrderDTO;
import org.vinn.openECommerce.api.order.dto.OrderItemRequest;
import org.vinn.openECommerce.api.order.model.Order;
import org.vinn.openECommerce.api.order.repository.OrderRepository;
import org.vinn.openECommerce.api.order.service.OrderService;
import org.vinn.openECommerce.api.order.util.OrderStatus;
import org.vinn.openECommerce.api.orderItem.model.OrderItem;
import org.vinn.openECommerce.api.product.model.Product;
import org.vinn.openECommerce.api.product.repository.ProductRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Override
    public OrderDTO createOrder(AddOrderRequest addOrderRequest) {
        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);

        List<OrderItem> orderItems = addOrderRequest.getOrderItems().stream()
                .map(this::mapToOrderItem)
                .collect(Collectors.toList());

        order.setOrderItems(orderItems);
        orderItems.forEach(item -> item.setOrder(order));

        Order savedOrder = orderRepository.save(order);
        return modelMapper.map(savedOrder, OrderDTO.class);
    }

    public OrderDTO changeOrderStatus(Long id, ChangeOrderStatusRequest request) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isEmpty()) {
            throw new IllegalArgumentException("Order not found with ID: " + id);
        }

        Order order = optionalOrder.get();
        order.setStatus(request.getStatus());
        Order updatedOrder = orderRepository.save(order);

        return modelMapper.map(updatedOrder, OrderDTO.class);
    }

    @Override
    public OrderDTO cancelOrder(Long id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isEmpty()) {
            throw new IllegalArgumentException("Order not found with ID: " + id);
        }

        Order order = optionalOrder.get();

        if (order.getStatus() == OrderStatus.SHIPPED || order.getStatus() == OrderStatus.DELIVERED) {
            throw new IllegalStateException("Order cannot be canceled as it is already " + order.getStatus());
        }

        order.setStatus(OrderStatus.CANCELED);
        Order updatedOrder = orderRepository.save(order);

        return modelMapper.map(updatedOrder, OrderDTO.class);
    }

    private OrderItem mapToOrderItem(OrderItemRequest request) {
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + request.getProductId()));

        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);
        orderItem.setQuantity(request.getQuantity());
        orderItem.setPriceAtPurchase(product.getPrice());
        orderItem.setTotalPrice(product.getPrice().multiply(new BigDecimal(request.getQuantity())));
        return orderItem;
    }

    @Override
    public OrderDTO getOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with ID: " + id));
        return modelMapper.map(order, OrderDTO.class);
    }

    @Override
    public List<OrderDTO> getOrders() {
        return orderRepository.findAll().stream()
                .map(order -> modelMapper.map(order, OrderDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with ID: " + id));
        orderRepository.delete(order);
    }
}
