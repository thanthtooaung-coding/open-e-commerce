package org.vinn.eCommerceAdmin.api.systemAdmin.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.vinn.eCommerceAdmin.api.systemAdmin.dto.SystemAdminDTO;
import org.vinn.eCommerceAdmin.api.systemAdmin.model.SystemAdmin;
import org.vinn.eCommerceAdmin.api.systemAdmin.repository.SystemAdminServiceRepository;
import org.vinn.eCommerceAdmin.api.systemAdmin.service.SystemAdminService;
import org.vinn.eCommerceAdmin.exception.EntityNotFoundException;
import org.vinn.eCommerceAdmin.exception.SystemAdminServiceException;
import org.vinn.eCommerceAdmin.security.PasswordEncoderService;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SystemAdminServiceImpl implements SystemAdminService {

    private final SystemAdminServiceRepository systemAdminServiceRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoderService passwordEncoderService;

    public SystemAdminServiceImpl(SystemAdminServiceRepository systemAdminServiceRepository, ModelMapper modelMapper, PasswordEncoderService passwordEncoderService) {
        this.systemAdminServiceRepository = systemAdminServiceRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoderService = passwordEncoderService;
    }

    @Override
    public SystemAdminDTO addSystemAdmin(SystemAdminDTO systemAdminDTO) {
        try {
            SystemAdmin systemAdmin = modelMapper.map(systemAdminDTO, SystemAdmin.class);
            String uniqueUsername = generateUniqueUsername(systemAdminDTO.getName());
            systemAdmin.setUsername(uniqueUsername);
            systemAdmin.setPassword(passwordEncoderService.encode("Admin123!@#"));
            systemAdmin.setEnabled(true);

            SystemAdmin savedSystemAdmin = systemAdminServiceRepository.save(systemAdmin);
            return modelMapper.map(savedSystemAdmin, SystemAdminDTO.class);
        }
        catch (EntityNotFoundException entityNotFoundException) {
            throw new EntityNotFoundException(entityNotFoundException.getMessage());
        } catch (DataAccessException e) {
            throw new SystemAdminServiceException("Failed to save system admin", e);
        }
    }

    private String generateUniqueUsername(String name) {
        String baseUsername = name.trim().replaceAll("\\s+", "").toLowerCase();
        String username = baseUsername;
        int counter = 1;

        while (systemAdminServiceRepository.findByUsername(username).isPresent()) {
            username = baseUsername + counter++;
        }

        return username;
    }

    @Override
    public SystemAdminDTO updateSystemAdmin(SystemAdminDTO systemAdmin) {
        try {
            checkSystemAdminExists(systemAdmin.getId());
            SystemAdmin savedSystemAdmin = systemAdminServiceRepository.save(modelMapper.map(systemAdmin, SystemAdmin.class));
            return modelMapper.map(savedSystemAdmin, SystemAdminDTO.class);
        } catch (DataAccessException e) {
            throw new SystemAdminServiceException("Failed to save system admin", e);
        }
    }

    @Override
    public SystemAdminDTO getSystemAdmin(String systemAdminId) {
        Long id = Long.parseLong(systemAdminId);
        SystemAdmin systemAdmin = systemAdminServiceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("SystemAdmin not found with id: " + id));
        return modelMapper.map(systemAdmin, SystemAdminDTO.class);
    }

    @Override
    public List<SystemAdminDTO> getSystemAdmins() {
        List<SystemAdmin> systemAdmins = systemAdminServiceRepository.findAll();
        if (systemAdmins.isEmpty()) {
            return Collections.emptyList();
        }

        return systemAdmins.stream()
                .filter(this::isActiveSystemAdmin)
                .map(systemAdmin -> modelMapper.map(systemAdmin, SystemAdminDTO.class))
                .collect(Collectors.toList());
    }

    private boolean isActiveSystemAdmin(SystemAdmin systemAdmin) {
        return systemAdmin.isEnabled();
    }

    @Override
    public void deleteSystemAdmin(String systemAdminId) {
        try {
            checkSystemAdminExists(Long.parseLong(systemAdminId));
            systemAdminServiceRepository.deleteById(Long.parseLong(systemAdminId));
        } catch (EntityNotFoundException entityNotFoundException) {
            throw new EntityNotFoundException(entityNotFoundException.getMessage());
        }
    }

    private void checkSystemAdminExists(Long systemAdminId) {
        boolean isAdminNull = !systemAdminServiceRepository.existsById(systemAdminId);
        if (!isAdminNull) {
            throw new EntityNotFoundException("System admin not found");
        }
    }
}
