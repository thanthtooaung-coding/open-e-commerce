package org.vinn.eCommerceAdmin.api.login.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.vinn.eCommerceAdmin.api.login.service.LoginService;

@RestController
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        if (loginService.login(username, password)) {
            return ResponseEntity.ok("Welcome, " + username + "!");
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}
