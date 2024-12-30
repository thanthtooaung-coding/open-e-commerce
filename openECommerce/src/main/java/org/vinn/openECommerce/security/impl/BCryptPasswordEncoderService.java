package org.vinn.openECommerce.security.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.vinn.openECommerce.security.PasswordEncoderService;

@Service
public class BCryptPasswordEncoderService implements PasswordEncoderService {
    private final BCryptPasswordEncoder passwordEncoder;

    public BCryptPasswordEncoderService() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public String encode(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    @Override
    public boolean matches(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
