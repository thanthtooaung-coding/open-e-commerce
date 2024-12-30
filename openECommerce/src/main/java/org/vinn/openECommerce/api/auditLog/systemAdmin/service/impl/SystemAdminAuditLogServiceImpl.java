package org.vinn.openECommerce.api.auditLog.systemAdmin.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.vinn.openECommerce.api.auditLog.systemAdmin.dto.SystemAdminAuditLogDTO;
import org.vinn.openECommerce.api.auditLog.systemAdmin.model.SystemAdminAuditLog;
import org.vinn.openECommerce.api.auditLog.systemAdmin.repository.SystemAdminAuditLogRepository;
import org.vinn.openECommerce.api.auditLog.systemAdmin.service.SystemAdminAuditLogService;
import org.vinn.openECommerce.api.auditLog.systemAdmin.util.SystemAdminAuditLogEntityType;

@Slf4j
@Service
public class SystemAdminAuditLogServiceImpl implements SystemAdminAuditLogService {

    private final SystemAdminAuditLogRepository systemAdminAuditLogRepository;
    private final ModelMapper modelMapper;

    public SystemAdminAuditLogServiceImpl(SystemAdminAuditLogRepository systemAdminAuditLogRepository, ModelMapper modelMapper) {
        this.systemAdminAuditLogRepository = systemAdminAuditLogRepository;
        this.modelMapper = modelMapper;
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

        log.atInfo().log("Creating audit log: Action={}, EntityType={}, EntityId={}", action, systemAdminAuditLogEntityType.name(), entityId);
        systemAdminAuditLogRepository.save(modelMapper.map(systemAdminAuditLogDTO, SystemAdminAuditLog.class));
    }
}
