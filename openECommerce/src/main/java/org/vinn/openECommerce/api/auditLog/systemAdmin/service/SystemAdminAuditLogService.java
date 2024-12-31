package org.vinn.openECommerce.api.auditLog.systemAdmin.service;

import org.vinn.openECommerce.api.auditLog.systemAdmin.dto.SystemAdminAuditLogDTO;

public interface SystemAdminAuditLogService {
    void createSystemAdminAuditLog(SystemAdminAuditLogDTO systemAdminAuditLogDTO);
}
