package org.vinn.openECommerce.api.analytics.pageOwner.service;

import java.util.Map;

public interface PageOwnerAnalyticsService {

    /**
     * Get an overview of staff managed by the PageOwner.
     *
     * @return a map of staff analytics
     */
    Map<String, Object> getStaffOverview();

    /**
     * Get product-related performance metrics.
     *
     * @return a map of product performance data
     */
    Map<String, Object> getProductPerformance();

    /**
     * Get order statistics.
     *
     * @return a map of order statistics
     */
    Map<String, Object> getOrderStatistics();

    /**
     * Get activity data for announcements.
     *
     * @return a map of announcement activity
     */
    Map<String, Object> getAnnouncementActivity();
}
