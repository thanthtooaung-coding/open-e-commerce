package org.vinn.openECommerce.api.user.staff.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.vinn.openECommerce.api.role.model.Role;
import org.vinn.openECommerce.api.role.repository.RoleRepository;
import org.vinn.openECommerce.api.user.common.service.impl.BaseUserServiceImpl;
import org.vinn.openECommerce.api.user.staff.dto.AddStaffRequest;
import org.vinn.openECommerce.api.user.staff.dto.StaffDTO;
import org.vinn.openECommerce.api.user.staff.dto.UpdateStaffRequest;
import org.vinn.openECommerce.api.user.staff.model.Staff;
import org.vinn.openECommerce.api.user.staff.repository.StaffRepository;
import org.vinn.openECommerce.api.user.staff.service.StaffService;
import org.vinn.openECommerce.exception.EntityNotFoundException;
import org.vinn.openECommerce.security.PasswordEncoderService;

@Service
public class StaffServiceImpl extends BaseUserServiceImpl<Staff, StaffDTO, AddStaffRequest, UpdateStaffRequest> implements StaffService {

    private final StaffRepository staffRepository;
    private final RoleRepository roleRepository;

    @Value("${staff.image-url}")
    private String staffImageURL;

    @Value("${staff.password}")
    private String staffPassword;

    public StaffServiceImpl(StaffRepository repository,
                            ModelMapper modelMapper,
                            PasswordEncoderService passwordEncoderService,
                            RoleRepository roleRepository) {
        super(repository, modelMapper, passwordEncoderService);
        this.staffRepository = repository;
        this.roleRepository = roleRepository;
    }

    @Override
    protected void initializeNewUser(Staff userEntity) {
        userEntity.setPassword(passwordEncoderService.encode(staffPassword));
        userEntity.setEnabled(true);

        String uniqueUsername = generateUniqueUsername(userEntity.getName());
        userEntity.setUsername(uniqueUsername);

        Role adminRole = roleRepository.findByName("ROLE_PAGE_STAFF")
                .orElseThrow(() -> new EntityNotFoundException("Role 'ROLE_PAGE_STAFF' not found"));
        userEntity.setRole(adminRole);

        userEntity.setImageUrl(userEntity.getImageUrl() != null ? userEntity.getImageUrl() : staffImageURL);
    }

    @Override
    protected boolean isUsernameExists(String username) {
        return staffRepository.findByUsername(username).isPresent();
    }

    @Override
    protected Class<Staff> getEntityClass() { return Staff.class; }

    @Override
    protected Class<StaffDTO> getDtoClass() { return StaffDTO.class; }

    @Override
    protected Long getIdFromDto(StaffDTO userDto) { return userDto.getId(); }

    @Override
    protected boolean isActiveUser(Staff userEntity) { return userEntity.isEnabled(); }

    @Override
    protected void updatePassword(Staff userEntity, String newPassword) {
        userEntity.setPassword(passwordEncoderService.encode(newPassword));
    }


}
