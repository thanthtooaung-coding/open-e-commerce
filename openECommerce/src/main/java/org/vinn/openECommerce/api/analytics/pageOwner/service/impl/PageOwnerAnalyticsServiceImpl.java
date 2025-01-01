package org.vinn.openECommerce.api.analytics.pageOwner.service.impl;

import org.springframework.stereotype.Service;
import org.vinn.openECommerce.api.analytics.pageOwner.service.PageOwnerAnalyticsService;
import org.vinn.openECommerce.api.announcement.repository.AnnouncementRepository;
import org.vinn.openECommerce.api.order.repository.OrderRepository;
import org.vinn.openECommerce.api.order.util.OrderStatus;
import org.vinn.openECommerce.api.product.repository.ProductRepository;
import org.vinn.openECommerce.api.user.staff.repository.StaffRepository;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

@Service
public class PageOwnerAnalyticsServiceImpl implements PageOwnerAnalyticsService {

    private final StaffRepository staffRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final AnnouncementRepository announcementRepository;

    public PageOwnerAnalyticsServiceImpl(
            StaffRepository staffRepository,
            ProductRepository productRepository,
            OrderRepository orderRepository,
            AnnouncementRepository announcementRepository) {
        this.staffRepository = staffRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.announcementRepository = announcementRepository;
    }

    @Override
    public Map<String, Object> getStaffOverview() {
        Map<String, Object> overview = new HashMap<>();
        overview.put("totalStaff", staffRepository.count());
        overview.put("activeStaff", staffRepository.countByEnabled(true));
        overview.put("recentlyAddedStaff", staffRepository.findRecentStaff(Instant.now().minus(30, ChronoUnit.DAYS)));
        return overview;
    }

    @Override
    public Map<String, Object> getProductPerformance() {
        Map<String, Object> performance = new HashMap<>();
        performance.put("totalProducts", productRepository.count());
        performance.put("lowStockProducts", productRepository.findLowStockProducts(5));
        performance.put("topSellingProducts", productRepository.findTopSellingProducts());
        return performance;
    }

    @Override
    public Map<String, Object> getOrderStatistics() {
        Map<String, Object> statistics = new HashMap<>();
        statistics.put("totalOrders", orderRepository.count());
        statistics.put("pendingOrders", orderRepository.countByStatus(OrderStatus.PENDING));
        statistics.put("completedOrders", orderRepository.countByStatus(OrderStatus.CONFIRMED));
        statistics.put("revenue", orderRepository.calculateTotalRevenue());
        return statistics;
    }

    @Override
    public Map<String, Object> getAnnouncementActivity() {
        Map<String, Object> activity = new HashMap<>();
        activity.put("totalAnnouncements", announcementRepository.count());
        activity.put("recentAnnouncements", announcementRepository.findRecentAnnouncements(Instant.now().minus(30, ChronoUnit.DAYS)));
        return activity;
    }
}
