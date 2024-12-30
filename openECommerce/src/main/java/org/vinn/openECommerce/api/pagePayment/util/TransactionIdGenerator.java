package org.vinn.openECommerce.api.pagePayment.util;

import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Utility class for generating unique transaction IDs.
 */
@Component
public class TransactionIdGenerator {

    /**
     * Generates a unique transaction ID.
     *
     * @return a string representing a unique transaction ID.
     */
    public String generateTransactionId() {
        return "TXN-" + UUID.randomUUID().toString().toUpperCase();
    }
}
