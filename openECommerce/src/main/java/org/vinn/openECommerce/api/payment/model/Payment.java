package org.vinn.openECommerce.api.payment.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.vinn.openECommerce.api.user.pageOwner.model.PageOwner;
import org.vinn.openECommerce.api.user.systemAdmin.model.SystemAdmin;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(length = 45)
    private String status;

    @Column(length = 45)
    private String transactionId;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private Instant createdAt;

    @Column(insertable = false)
    @LastModifiedDate
    private Instant updatedAt;

    @ManyToOne
    @JoinColumn(name = "system_admin_id")
    private SystemAdmin systemAdmin;

    @ManyToOne
    @JoinColumn(name = "page_owner_id")
    private PageOwner pageOwner;

    @ManyToOne
    @JoinColumn(name = "payment_method_id")
    private PaymentMethod paymentMethod;
}