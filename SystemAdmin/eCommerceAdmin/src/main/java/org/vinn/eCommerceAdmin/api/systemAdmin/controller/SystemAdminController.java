package org.vinn.eCommerceAdmin.api.systemAdmin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.vinn.eCommerceAdmin.api.systemAdmin.dto.SystemAdminDTO;

@RestController
public class SystemAdminController {

    @PostMapping
    public ResponseEntity<SystemAdminDTO> addSystemAdmin(@RequestBody SystemAdminDTO systemAdminDTO) {
        return ResponseEntity.ok(systemAdminDTO);
    }
}
