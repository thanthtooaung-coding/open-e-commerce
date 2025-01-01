package org.vinn.openECommerce.api.analytics.pageOwner.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vinn.openECommerce.api.analytics.pageOwner.service.PageOwnerAnalyticsService;

import java.util.Map;

@RestController
@RequestMapping("${api.base.path}/page-owner-analytics")
public class PageOwnerAnalyticsController {

    private final PageOwnerAnalyticsService analyticsService;

    public PageOwnerAnalyticsController(PageOwnerAnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping("/staff-overview")
    public Map<String, Object> getStaffOverview() {
        return analyticsService.getStaffOverview();
    }

    @GetMapping("/product-performance")
    public Map<String, Object> getProductPerformance() {
        return analyticsService.getProductPerformance();
    }

    @GetMapping("/order-statistics")
    public Map<String, Object> getOrderStatistics() {
        return analyticsService.getOrderStatistics();
    }

    @GetMapping("/announcement-activity")
    public Map<String, Object> getAnnouncementActivity() {
        return analyticsService.getAnnouncementActivity();
    }
}
