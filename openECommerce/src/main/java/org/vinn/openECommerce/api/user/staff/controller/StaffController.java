package org.vinn.openECommerce.api.user.staff.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vinn.openECommerce.api.user.staff.dto.AddStaffRequest;
import org.vinn.openECommerce.api.user.staff.dto.StaffDTO;
import org.vinn.openECommerce.api.user.staff.dto.UpdateStaffRequest;
import org.vinn.openECommerce.api.user.staff.service.StaffService;

import java.util.List;

@RestController
@RequestMapping("${api.base.path}/staff")
public class StaffController {

    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @PostMapping
    public ResponseEntity<StaffDTO> createStaff(@Valid @RequestBody AddStaffRequest staffDTO) {
        StaffDTO createdStaff = staffService.addUser(staffDTO);
        return ResponseEntity.ok(createdStaff);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StaffDTO> updateStaff(
            @Valid
            @PathVariable Long id,
            @RequestBody UpdateStaffRequest staffDTO) {
        staffDTO.setId(id);
        StaffDTO updatedStaff = staffService.updateUser(staffDTO);
        return ResponseEntity.ok(updatedStaff);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StaffDTO> getStaff(@PathVariable Long id) {
        StaffDTO staff = staffService.getUser(id.toString());
        return ResponseEntity.ok(staff);
    }

    @GetMapping
    public ResponseEntity<List<StaffDTO>> getAllStaff() {
        List<StaffDTO> staffList = staffService.getAllUsers();
        return ResponseEntity.ok(staffList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStaff(@PathVariable Long id) {
        staffService.deleteUser(id.toString());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/change-password")
    public ResponseEntity<Void> changeStaffPassword(
            @PathVariable Long id,
            @RequestBody String newPassword) {
        staffService.changePassword(id.toString(), newPassword);
        return ResponseEntity.noContent().build();
    }
}
