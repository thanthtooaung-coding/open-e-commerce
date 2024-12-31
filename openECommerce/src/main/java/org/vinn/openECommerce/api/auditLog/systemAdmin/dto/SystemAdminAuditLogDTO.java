package org.vinn.openECommerce.api.auditLog.systemAdmin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.vinn.openECommerce.api.auditLog.systemAdmin.util.SystemAdminAuditLogEntityType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SystemAdminAuditLogDTO {

    private String action;

    private SystemAdminAuditLogEntityType systemAdminAuditLogEntityType;

    private String entityId;
}
