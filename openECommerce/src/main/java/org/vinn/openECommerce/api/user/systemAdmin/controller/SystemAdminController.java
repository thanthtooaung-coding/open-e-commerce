package org.vinn.openECommerce.api.user.systemAdmin.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vinn.openECommerce.api.user.systemAdmin.dto.AddSystemAdminRequest;
import org.vinn.openECommerce.api.user.systemAdmin.dto.SystemAdminDTO;
import org.vinn.openECommerce.api.user.systemAdmin.dto.UpdateSystemAdminRequest;
import org.vinn.openECommerce.api.user.systemAdmin.service.SystemAdminService;

import java.util.List;

@RestController
@RequestMapping("${api.base.path}/system-admins")
public class SystemAdminController {

    private final SystemAdminService systemAdminService;

    public SystemAdminController(SystemAdminService systemAdminService) {
        this.systemAdminService = systemAdminService;
    }

    @PostMapping
    public ResponseEntity<SystemAdminDTO> createSystemAdmin(@Valid @RequestBody AddSystemAdminRequest systemAdminDTO) {
        SystemAdminDTO createdSystemAdmin = systemAdminService.addUser(systemAdminDTO);
        return ResponseEntity.ok(createdSystemAdmin);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SystemAdminDTO> updateSystemAdmin(
            @Valid
            @PathVariable Long id,
            @RequestBody UpdateSystemAdminRequest systemAdminDTO) {
        systemAdminDTO.setId(id);
        SystemAdminDTO updatedSystemAdmin = systemAdminService.updateUser(systemAdminDTO);
        return ResponseEntity.ok(updatedSystemAdmin);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SystemAdminDTO> getSystemAdmin(@PathVariable Long id) {
        SystemAdminDTO systemAdmin = systemAdminService.getUser(id.toString());
        return ResponseEntity.ok(systemAdmin);
    }

    @GetMapping
    public ResponseEntity<List<SystemAdminDTO>> getAllSystemAdmins() {
        List<SystemAdminDTO> systemAdmins = systemAdminService.getAllUsers();
        return ResponseEntity.ok(systemAdmins);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSystemAdmin(@PathVariable Long id) {
        systemAdminService.deleteUser(id.toString());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/change-password")
    public ResponseEntity<Void> changeSystemAdminPassword(
            @PathVariable Long id,
            @RequestBody String newPassword) {
        systemAdminService.changePassword(id.toString(), newPassword);
        return ResponseEntity.noContent().build();
    }
}
