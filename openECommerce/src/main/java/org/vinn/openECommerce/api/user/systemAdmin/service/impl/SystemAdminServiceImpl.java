package org.vinn.openECommerce.api.user.systemAdmin.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.vinn.openECommerce.api.role.model.Role;
import org.vinn.openECommerce.api.role.repository.RoleRepository;
import org.vinn.openECommerce.api.user.common.service.impl.BaseUserServiceImpl;
import org.vinn.openECommerce.api.user.systemAdmin.dto.AddSystemAdminRequest;
import org.vinn.openECommerce.api.user.systemAdmin.dto.SystemAdminDTO;
import org.vinn.openECommerce.api.user.systemAdmin.dto.UpdateSystemAdminRequest;
import org.vinn.openECommerce.api.user.systemAdmin.model.SystemAdmin;
import org.vinn.openECommerce.api.user.systemAdmin.repository.SystemAdminRepository;
import org.vinn.openECommerce.api.user.systemAdmin.service.SystemAdminService;
import org.vinn.openECommerce.exception.EntityNotFoundException;
import org.vinn.openECommerce.security.PasswordEncoderService;

@Service
public class SystemAdminServiceImpl extends BaseUserServiceImpl<SystemAdmin, SystemAdminDTO, AddSystemAdminRequest, UpdateSystemAdminRequest> implements SystemAdminService {

    private final SystemAdminRepository systemAdminRepository;
    private final RoleRepository roleRepository;

    @Value("${system.admin.image-url}")
    private String systemAdminImageURL;

    @Value("${system.admin.password}")
    private String staffPassword;

    public SystemAdminServiceImpl(SystemAdminRepository repository,
                                  ModelMapper modelMapper,
                                  PasswordEncoderService passwordEncoderService,
                                  RoleRepository roleRepository) {
        super(repository, modelMapper, passwordEncoderService);
        this.systemAdminRepository = repository;
        this.roleRepository = roleRepository;
    }

    @Override
    protected void initializeNewUser(SystemAdmin userEntity) {
        userEntity.setPassword(passwordEncoderService.encode(staffPassword));
        userEntity.setEnabled(true);

        String uniqueUsername = generateUniqueUsername(userEntity.getName());
        userEntity.setUsername(uniqueUsername);

        Role adminRole = roleRepository.findByName("ROLE_SYSTEM_ADMIN")
                .orElseThrow(() -> new EntityNotFoundException("Role 'ROLE_SYSTEM_ADMIN' not found"));
        userEntity.setRole(adminRole);

        userEntity.setImageUrl(userEntity.getImageUrl() != null ? userEntity.getImageUrl() : systemAdminImageURL);
    }

    @Override
    protected boolean isUsernameExists(String username) {
        return systemAdminRepository.findByUsername(username).isPresent();
    }

    @Override
    protected Class<SystemAdmin> getEntityClass() {
        return SystemAdmin.class;
    }

    @Override
    protected Class<SystemAdminDTO> getDtoClass() {
        return SystemAdminDTO.class;
    }

    @Override
    protected Long getIdFromDto(SystemAdminDTO userDto) {
        return userDto.getId();
    }

    @Override
    protected boolean isActiveUser(SystemAdmin userEntity) {
        return userEntity.isEnabled();
    }

    @Override
    protected void updatePassword(SystemAdmin userEntity, String newPassword) {
        userEntity.setPassword(passwordEncoderService.encode(newPassword));
    }
}
