package org.vinn.openECommerce.api.user.common.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.vinn.openECommerce.api.user.common.service.BaseUserService;
import org.vinn.openECommerce.exception.EntityNotFoundException;
import org.vinn.openECommerce.security.PasswordEncoderService;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseUserServiceImpl<T, DTO, AddDTO, UpdateDTO> implements BaseUserService<T, DTO, AddDTO, UpdateDTO> {

    protected final JpaRepository<T, Long> repository;
    protected final ModelMapper modelMapper;
    protected final PasswordEncoderService passwordEncoderService;

    public BaseUserServiceImpl(JpaRepository<T, Long> repository, ModelMapper modelMapper, PasswordEncoderService passwordEncoderService) {
        this.repository = repository;
        this.modelMapper = modelMapper;
        this.passwordEncoderService = passwordEncoderService;
    }

    @Override
    public DTO addUser(AddDTO userDto) {
        T userEntity = modelMapper.map(userDto, getEntityClass());
        initializeNewUser(userEntity);
        T savedEntity = repository.save(userEntity);
        return modelMapper.map(savedEntity, getDtoClass());
    }

    @Override
    public DTO updateUser(UpdateDTO userDto) {
        T userEntity = modelMapper.map(userDto, getEntityClass());
        checkUserExists(getIdFromDto(modelMapper.map(userDto, getDtoClass())));
        T updatedEntity = repository.save(userEntity);
        return modelMapper.map(updatedEntity, getDtoClass());
    }

    @Override
    public DTO getUser(String userId) {
        T userEntity = findById(Long.parseLong(userId));
        return modelMapper.map(userEntity, getDtoClass());
    }

    @Override
    public List<DTO> getAllUsers() {
        List<T> users = repository.findAll();
        return users.isEmpty()
                ? Collections.emptyList()
                : users.stream().filter(this::isActiveUser).map(user -> modelMapper.map(user, getDtoClass())).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(String userId) {
        Long id = Long.parseLong(userId);
        checkUserExists(id);
        repository.deleteById(id);
    }

    @Override
    public void changePassword(String userId, String newPassword) {
        T userEntity = findById(Long.parseLong(userId));
        updatePassword(userEntity, newPassword);
        repository.save(userEntity);
    }

    protected String generateUniqueUsername(String name) {
        String baseUsername = name.trim().replaceAll("\\s+", "").toLowerCase();
        String username = baseUsername;
        int counter = 1;

        while (isUsernameExists(username)) {
            username = baseUsername + counter++;
        }

        return username;
    }

    protected abstract boolean isUsernameExists(String username);

    private T findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
    }

    private void checkUserExists(Long userId) {
        if (!repository.existsById(userId)) {
            throw new EntityNotFoundException("User not found with id: " + userId);
        }
    }

    protected abstract void initializeNewUser(T userEntity);

    protected abstract Class<T> getEntityClass();

    protected abstract Class<DTO> getDtoClass();

    protected abstract Long getIdFromDto(DTO userDto);

    protected abstract boolean isActiveUser(T userEntity);

    protected abstract void updatePassword(T userEntity, String newPassword);
}
