package org.vinn.openECommerce.api.auditLog.systemAdmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vinn.openECommerce.api.auditLog.systemAdmin.model.SystemAdminAuditLog;

public interface SystemAdminAuditLogRepository extends JpaRepository<SystemAdminAuditLog, Long> {
}
