package org.vinn.eCommerceAdmin.api.systemAdmin.service;

import org.vinn.eCommerceAdmin.api.systemAdmin.dto.SystemAdminDTO;

import java.util.List;

public interface SystemAdminService {
    SystemAdminDTO addSystemAdmin(SystemAdminDTO systemAdmin);
    SystemAdminDTO updateSystemAdmin(SystemAdminDTO systemAdmin);
    SystemAdminDTO getSystemAdmin(String systemAdminId);
    List<SystemAdminDTO> getSystemAdmins();
    void deleteSystemAdmin(String systemAdminId);
}
