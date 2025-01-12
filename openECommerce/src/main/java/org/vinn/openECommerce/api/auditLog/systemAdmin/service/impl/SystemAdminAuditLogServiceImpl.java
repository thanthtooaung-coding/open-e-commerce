package org.vinn.openECommerce.api.auditLog.systemAdmin.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.vinn.openECommerce.api.auditLog.systemAdmin.dto.SystemAdminAuditLogDTO;
import org.vinn.openECommerce.api.auditLog.systemAdmin.model.SystemAdminAuditLog;
import org.vinn.openECommerce.api.auditLog.systemAdmin.repository.SystemAdminAuditLogRepository;
import org.vinn.openECommerce.api.auditLog.systemAdmin.service.SystemAdminAuditLogService;
import org.vinn.openECommerce.api.auditLog.systemAdmin.util.SystemAdminAuditLogEntityType;

import java.time.Instant;

@Slf4j
@Service
@Transactional
public class SystemAdminAuditLogServiceImpl implements SystemAdminAuditLogService {

    private final SystemAdminAuditLogRepository systemAdminAuditLogRepository;

    public SystemAdminAuditLogServiceImpl(SystemAdminAuditLogRepository systemAdminAuditLogRepository) {
        this.systemAdminAuditLogRepository = systemAdminAuditLogRepository;
    }

    @Override
    public void createSystemAdminAuditLog(SystemAdminAuditLogDTO systemAdminAuditLogDTO) {
        if (systemAdminAuditLogDTO == null) {
            log.atWarn().log("SystemAdminAuditLogDTO is null. Cannot create audit log.");
            return;
        }

        String action = systemAdminAuditLogDTO.getAction();
        SystemAdminAuditLogEntityType systemAdminAuditLogEntityType = systemAdminAuditLogDTO.getSystemAdminAuditLogEntityType();
        String entityId = systemAdminAuditLogDTO.getEntityId();

        SystemAdminAuditLog systemAdminAuditLog = new SystemAdminAuditLog();
        systemAdminAuditLog.setAction(action);
        systemAdminAuditLog.setEntityId(entityId);
        systemAdminAuditLog.setSystemAdminAuditLogEntityType(systemAdminAuditLogEntityType);
        systemAdminAuditLog.setCreatedAt(Instant.now());

        log.atInfo().log("Creating audit log: Action={}, EntityType={}, EntityId={}", action, systemAdminAuditLogEntityType.name(), entityId);
        systemAdminAuditLogRepository.save(systemAdminAuditLog);
    }
}
