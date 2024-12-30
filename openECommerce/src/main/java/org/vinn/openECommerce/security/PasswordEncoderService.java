package org.vinn.openECommerce.security;

public interface PasswordEncoderService {
    String encode(String rawPassword);
    boolean matches(String rawPassword, String encodedPassword);
}
