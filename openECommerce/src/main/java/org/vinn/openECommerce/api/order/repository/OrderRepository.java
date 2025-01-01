package org.vinn.openECommerce.api.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vinn.openECommerce.api.order.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
