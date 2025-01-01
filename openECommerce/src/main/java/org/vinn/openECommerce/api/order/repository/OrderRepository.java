package org.vinn.openECommerce.api.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vinn.openECommerce.api.order.model.Order;
import org.vinn.openECommerce.api.order.util.OrderStatus;

public interface OrderRepository extends JpaRepository<Order, Long> {
    long countByStatus(OrderStatus status);

    @Query("SELECT SUM(oi.totalPrice) FROM OrderItem oi")
    BigDecimal calculateTotalRevenue();
}
