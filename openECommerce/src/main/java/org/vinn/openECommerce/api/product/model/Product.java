package org.vinn.openECommerce.api.product.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.vinn.openECommerce.api.orderItem.model.OrderItem;
import org.vinn.openECommerce.api.page.model.Page;
import org.vinn.openECommerce.api.productCategory.model.ProductCategory;
import org.vinn.openECommerce.api.user.staff.model.Staff;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false, length = 45, unique = true)
    private String code;

    @Column(nullable = false)
    private String imageUrl;

    @Column(precision = 10, scale = 2)
    private BigDecimal price;

    private int stockQuantity;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private Instant createdAt;

    @Column(insertable = false)
    @LastModifiedDate
    private Instant updatedAt;

    @ManyToOne
    @JoinColumn(name = "product_category_id")
    private ProductCategory productCategory;

    @ManyToOne
    @JoinColumn(name = "page_id", nullable = false)
    private Page page;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;

    @OneToMany(mappedBy = "product")
    private List<OrderItem> orderItems;
}
