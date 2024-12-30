package org.vinn.openECommerce.api.user.pageOwner.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vinn.openECommerce.api.user.pageOwner.dto.AddPageOwnerRequest;
import org.vinn.openECommerce.api.user.pageOwner.dto.PageOwnerDTO;
import org.vinn.openECommerce.api.user.pageOwner.dto.UpdatePageOwnerRequest;
import org.vinn.openECommerce.api.user.pageOwner.service.PageOwnerService;

import java.util.List;

@RestController
@RequestMapping("${api.base.path}/page-owners")
public class PageOwnerController {

    private final PageOwnerService pageOwnerService;

    public PageOwnerController(PageOwnerService pageOwnerService) {
        this.pageOwnerService = pageOwnerService;
    }

    @PostMapping
    public ResponseEntity<PageOwnerDTO> createPageOwner(@Valid @RequestBody AddPageOwnerRequest pageOwnerDTO) {
        PageOwnerDTO createdPageOwner = pageOwnerService.addUser(pageOwnerDTO);
        return ResponseEntity.ok(createdPageOwner);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PageOwnerDTO> updatePageOwner(
            @Valid
            @PathVariable Long id,
            @RequestBody UpdatePageOwnerRequest pageOwnerDTO) {
        pageOwnerDTO.setId(id);
        PageOwnerDTO updatedPageOwner = pageOwnerService.updateUser(pageOwnerDTO);
        return ResponseEntity.ok(updatedPageOwner);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PageOwnerDTO> getPageOwner(@PathVariable Long id) {
        PageOwnerDTO pageOwner = pageOwnerService.getUser(id.toString());
        return ResponseEntity.ok(pageOwner);
    }

    @GetMapping
    public ResponseEntity<List<PageOwnerDTO>> getAllPageOwners() {
        List<PageOwnerDTO> pageOwners = pageOwnerService.getAllUsers();
        return ResponseEntity.ok(pageOwners);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePageOwner(@PathVariable Long id) {
        pageOwnerService.deleteUser(id.toString());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/change-password")
    public ResponseEntity<Void> changePageOwnerPassword(
            @PathVariable Long id,
            @RequestBody String newPassword) {
        pageOwnerService.changePassword(id.toString(), newPassword);
        return ResponseEntity.noContent().build();
    }
}
