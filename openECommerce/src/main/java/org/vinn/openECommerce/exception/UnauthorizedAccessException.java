package org.vinn.openECommerce.exception;

/**
 * Exception thrown when an unauthorized access attempt is detected.
 */
public class UnauthorizedAccessException extends RuntimeException {
    public UnauthorizedAccessException(String message) {
        super(message);
    }
}
