package org.vinn.openECommerce.api.auditLog.systemAdmin.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.vinn.openECommerce.api.auditLog.systemAdmin.util.SystemAdminAuditLogEntityType;
import org.vinn.openECommerce.api.user.systemAdmin.model.SystemAdmin;

import java.time.Instant;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Entity
public class SystemAdminAuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String action;

    @Enumerated(EnumType.STRING)
    private SystemAdminAuditLogEntityType systemAdminAuditLogEntityType;

    @Column(nullable = false, length = 45)
    private String entityId;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private Instant createdAt;

    @ManyToOne
    @JoinColumn(name = "system_admin_id")
    private SystemAdmin systemAdmin;
}
