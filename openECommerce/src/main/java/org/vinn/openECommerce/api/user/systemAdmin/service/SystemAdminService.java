package org.vinn.openECommerce.api.user.systemAdmin.service;

import org.vinn.openECommerce.api.user.common.service.BaseUserService;
import org.vinn.openECommerce.api.user.systemAdmin.dto.AddSystemAdminRequest;
import org.vinn.openECommerce.api.user.systemAdmin.dto.SystemAdminDTO;
import org.vinn.openECommerce.api.user.systemAdmin.dto.UpdateSystemAdminRequest;
import org.vinn.openECommerce.api.user.systemAdmin.model.SystemAdmin;

public interface SystemAdminService extends BaseUserService<SystemAdmin, SystemAdminDTO, AddSystemAdminRequest, UpdateSystemAdminRequest> {
}
