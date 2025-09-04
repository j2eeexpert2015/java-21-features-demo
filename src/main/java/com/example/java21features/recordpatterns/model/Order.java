package com.example.java21features.recordpatterns.model;

import java.time.LocalDateTime;
import java.util.List;

// Simple order record with practical business logic
public record Order(
    String id,
    String customerName,
    List<String> items,
    double total,
    LocalDateTime date,
    String status, // PENDING, PAID, SHIPPED, DELIVERED
    String customerTier // BASIC, PREMIUM, VIP
) {
    // Business logic methods
    public boolean isHighValue() {
        return total > 1000.0;
    }
    
    public boolean containsElectronics() {
        return items.stream().anyMatch(item -> 
            item.toLowerCase().contains("iphone") || 
            item.toLowerCase().contains("laptop") ||
            item.toLowerCase().contains("tablet")
        );
    }
    
    public boolean isPriority() {
        return "VIP".equals(customerTier) || "PREMIUM".equals(customerTier);
    }
}