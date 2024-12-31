package org.vinn.openECommerce.api.user.pageOwner.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.vinn.openECommerce.api.role.model.Role;
import org.vinn.openECommerce.api.role.repository.RoleRepository;
import org.vinn.openECommerce.api.user.common.service.impl.BaseUserServiceImpl;
import org.vinn.openECommerce.api.user.pageOwner.dto.AddPageOwnerRequest;
import org.vinn.openECommerce.api.user.pageOwner.dto.PageOwnerDTO;
import org.vinn.openECommerce.api.user.pageOwner.dto.UpdatePageOwnerRequest;
import org.vinn.openECommerce.api.user.pageOwner.model.PageOwner;
import org.vinn.openECommerce.api.user.pageOwner.repository.PageOwnerRepository;
import org.vinn.openECommerce.api.user.pageOwner.service.PageOwnerService;
import org.vinn.openECommerce.exception.EntityNotFoundException;
import org.vinn.openECommerce.security.PasswordEncoderService;

@Service
public class PageOwnerServiceImpl extends BaseUserServiceImpl<PageOwner, PageOwnerDTO, AddPageOwnerRequest, UpdatePageOwnerRequest> implements PageOwnerService {

    private final PageOwnerRepository pageOwnerRepository;
    private final RoleRepository roleRepository;

    @Value("${page.owner.image-url}")
    private String pageOwnerImageURL;

    public PageOwnerServiceImpl(PageOwnerRepository repository,
                                ModelMapper modelMapper,
                                PasswordEncoderService passwordEncoderService,
                                RoleRepository roleRepository) {
        super(repository, modelMapper, passwordEncoderService);
        this.pageOwnerRepository = repository;
        this.roleRepository = roleRepository;
    }

    @Override
    protected void initializeNewUser(PageOwner userEntity) {
        userEntity.setPassword(passwordEncoderService.encode("Admin123!@#"));
        userEntity.setEnabled(true);

        String uniqueUsername = generateUniqueUsername(userEntity.getName());
        userEntity.setUsername(uniqueUsername);

        Role adminRole = roleRepository.findByName("ROLE_PAGE_OWNER")
                .orElseThrow(() -> new EntityNotFoundException("Role 'ROLE_PAGE_OWNER' not found"));
        userEntity.setRole(adminRole);

        userEntity.setImageUrl(userEntity.getImageUrl() != null ? userEntity.getImageUrl() : pageOwnerImageURL);
    }

    @Override
    protected boolean isUsernameExists(String username) {
        return pageOwnerRepository.findByUsername(username).isPresent();
    }

    @Override
    protected Class<PageOwner> getEntityClass() {
        return PageOwner.class;
    }

    @Override
    protected Class<PageOwnerDTO> getDtoClass() {
        return PageOwnerDTO.class;
    }

    @Override
    protected Long getIdFromDto(PageOwnerDTO userDto) {
        return userDto.getId();
    }

    @Override
    protected boolean isActiveUser(PageOwner userEntity) {
        return userEntity.isEnabled();
    }

    @Override
    protected void updatePassword(PageOwner userEntity, String newPassword) {
        userEntity.setPassword(passwordEncoderService.encode(newPassword));
    }
}
