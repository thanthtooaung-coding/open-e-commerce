package org.vinn.openECommerce.api.orderItem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.vinn.openECommerce.api.order.model.Order;
import org.vinn.openECommerce.api.product.model.Product;

import java.math.BigDecimal;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;
    private BigDecimal priceAtPurchase;
    private BigDecimal totalPrice;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}