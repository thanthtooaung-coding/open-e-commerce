package org.vinn.openECommerce.api.analytics.systemAdmin.service;

import java.util.Map;

public interface SystemAdminAnalyticsService {
    /**
     * Get platform-wide metrics like total pages, payments, and admin counts.
     *
     * @return a map of platform metrics
     */
    Map<String, Object> getPlatformMetrics();

    /**
     * Get statistics about pages, such as top owners and pages created over time.
     *
     * @return a map of page statistics
     */
    Map<String, Object> getPageStatistics();

    /**
     * Get payment-related statistics, such as total revenue and payment statuses.
     *
     * @return a map of payment statistics
     */
    Map<String, Object> getPaymentStatistics();

    /**
     * Get user activity data such as recent logins and new pages.
     *
     * @return a map of user activity
     */
    Map<String, Object> getUserActivity();
}
