package org.vinn.openECommerce.api.user.staff.service;

import org.vinn.openECommerce.api.user.common.service.BaseUserService;
import org.vinn.openECommerce.api.user.staff.dto.AddStaffRequest;
import org.vinn.openECommerce.api.user.staff.dto.StaffDTO;
import org.vinn.openECommerce.api.user.staff.dto.UpdateStaffRequest;
import org.vinn.openECommerce.api.user.staff.model.Staff;

public interface StaffService extends BaseUserService<Staff, StaffDTO, AddStaffRequest, UpdateStaffRequest> {
}
