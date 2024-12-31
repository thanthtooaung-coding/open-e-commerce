package org.vinn.openECommerce.api.user.common.service;

import java.util.List;

public interface BaseUserService<T, DTO, AddDTO, UpdateDTO> {
    DTO addUser(AddDTO userDto);
    DTO updateUser(UpdateDTO userDto);
    DTO getUser(String userId);
    List<DTO> getAllUsers();
    void deleteUser(String userId);
    void changePassword(String userId, String newPassword);
}
