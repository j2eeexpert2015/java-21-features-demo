package com.example.springapp.service;

import com.example.springapp.model.Order;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class OrderService {
    // Use LinkedHashMap but we'll need to access sequenced methods differently
    private final LinkedHashMap<Long, Order> orders = new LinkedHashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public OrderService() {
        // Initialize with some sample data
        createOrder(new Order(null, "Alice", 100.0, LocalDateTime.now().minusDays(3), "COMPLETED"));
        createOrder(new Order(null, "Bob", 250.0, LocalDateTime.now().minusDays(2), "PENDING"));
        createOrder(new Order(null, "Charlie", 75.0, LocalDateTime.now().minusDays(1), "COMPLETED"));
        createOrder(new Order(null, "Diana", 300.0, LocalDateTime.now(), "PROCESSING"));
    }

    public Order createOrder(Order order) {
        Long id = idCounter.getAndIncrement();
        order.setId(id);
        orders.put(id, order);
        return order;
    }

    public List<Order> getAllOrders() {
        return List.copyOf(orders.values());
    }

    public Order getOrderById(Long id) {
        return orders.get(id);
    }

    // Helper method to get first entry using traditional approach
    private Order getFirstOrder() {
        if (orders.isEmpty()) {
            return null;
        }
        return orders.entrySet().iterator().next().getValue();
    }

    // Helper method to get last entry using traditional approach
    private Order getLastOrder() {
        if (orders.isEmpty()) {
            return null;
        }
        Order lastOrder = null;
        for (Order order : orders.values()) {
            lastOrder = order;
        }
        return lastOrder;
    }

    // Using Sequenced Collections features for pagination metadata
    public Map<String, Object> getOrdersWithPagination(int page, int size) {
        List<Order> allOrders = getAllOrders();
        int totalItems = allOrders.size();
        int totalPages = (int) Math.ceil((double) totalItems / size);
        
        int start = Math.min((page - 1) * size, totalItems);
        int end = Math.min(start + size, totalItems);
        
        List<Order> pageData = allOrders.subList(start, end);
        
        // Build response with pagination metadata
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("data", pageData);
        response.put("currentPage", page);
        response.put("pageSize", size);
        response.put("totalItems", totalItems);
        response.put("totalPages", totalPages);
        
        // Use helper methods for first/last item metadata
        Order firstOrder = getFirstOrder();
        Order lastOrder = getLastOrder();
        
        if (firstOrder != null) {
            response.put("firstItemId", firstOrder.getId());
        }
        if (lastOrder != null) {
            response.put("lastItemId", lastOrder.getId());
        }
        
        response.put("hasPrevious", page > 1);
        response.put("hasNext", page < totalPages);
        
        return response;
    }
}