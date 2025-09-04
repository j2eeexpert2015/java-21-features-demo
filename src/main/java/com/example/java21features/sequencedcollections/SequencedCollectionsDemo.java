package com.example.java21features.sequencedcollections;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;

import com.example.java21features.sequencedcollections.model.Order;

public class SequencedCollectionsDemo {

    // Demo 1: LRU Cache using LinkedHashSet - manual implementation
    public void demoLinkedHashSetBoundedCache() {
        System.out.println("=== Demo 1: LRU Cache with LinkedHashSet ===");
        
        // Create a bounded cache that maintains insertion order
        LinkedHashSet<String> recentSearches = new LinkedHashSet<>();
        int maxSize = 5;

        // Simulate search operations
        String[] searches = {"java", "spring", "python", "java", "docker", "kubernetes", "spring"};
        
        for (String search : searches) {
            System.out.println("Processing search: " + search);
            
            // Remove if already exists (to update position)
            recentSearches.remove(search);
            
            // If cache is full, remove the oldest item (first element)
            if (recentSearches.size() >= maxSize) {
                // Manual implementation of getFirst() for LinkedHashSet
                String oldest = getFirstElement(recentSearches);
                recentSearches.remove(oldest);
                System.out.println("  Removed oldest: " + oldest);
            }
            
            // Add the new search (becomes the most recent)
            recentSearches.add(search);
            System.out.println("  Current cache: " + recentSearches);
        }

        System.out.println("Final cache: " + recentSearches);
        // Manual implementation of getLast() for LinkedHashSet
        System.out.println("Most recent: " + getLastElement(recentSearches));
        System.out.println();
    }

    // Helper method to get first element from LinkedHashSet
    private <T> T getFirstElement(LinkedHashSet<T> set) {
        if (set.isEmpty()) {
            return null;
        }
        return set.iterator().next();
    }

    // Helper method to get last element from LinkedHashSet
    private <T> T getLastElement(LinkedHashSet<T> set) {
        if (set.isEmpty()) {
            return null;
        }
        T lastElement = null;
        for (T element : set) {
            lastElement = element;
        }
        return lastElement;
    }

    // Demo 2: Pagination metadata using manual first/last methods
    public void demoPaginationWithSequencedMap() {
        System.out.println("=== Demo 2: Pagination with LinkedHashMap ===");
        
        // Create sample orders (in insertion order)
        LinkedHashMap<Integer, Order> orders = new LinkedHashMap<>();
        orders.put(1, new Order(1L, "Alice", 100.0, LocalDateTime.now().minusDays(3), "COMPLETED"));
        orders.put(2, new Order(2L, "Bob", 250.0, LocalDateTime.now().minusDays(2), "PENDING"));
        orders.put(3, new Order(3L, "Charlie", 75.0, LocalDateTime.now().minusDays(1), "COMPLETED"));
        orders.put(4, new Order(4L, "Diana", 300.0, LocalDateTime.now(), "PROCESSING"));
        orders.put(5, new Order(5L, "Eve", 150.0, LocalDateTime.now(), "PENDING"));

        // Simulate pagination parameters
        int page = 2;
        int pageSize = 2;
        int totalItems = orders.size();

        // Calculate pagination bounds
        int start = (page - 1) * pageSize;
        int end = Math.min(start + pageSize, totalItems);

        // Get page data
        List<Order> pageData = new ArrayList<>();
        int index = 0;
        for (Order order : orders.values()) {
            if (index >= start && index < end) {
                pageData.add(order);
            }
            index++;
        }

        // Build pagination metadata using manual methods
        System.out.println("Page " + page + " data: " + pageData);
        System.out.println("Total items: " + totalItems);
        
        // Manual implementation of getFirst() for LinkedHashMap values
        Order firstOrder = getFirstMapValue(orders);
        Order lastOrder = getLastMapValue(orders);
        
        System.out.println("First order ID: " + (firstOrder == null ? "N/A" : firstOrder.getId()));
        System.out.println("Last order ID: " + (lastOrder == null ? "N/A" : lastOrder.getId()));
        System.out.println("Has previous page: " + (page > 1));
        System.out.println("Has next page: " + (end < totalItems));
        System.out.println();
    }

    // Helper method to get first value from LinkedHashMap
    private <K, V> V getFirstMapValue(LinkedHashMap<K, V> map) {
        if (map.isEmpty()) {
            return null;
        }
        return map.values().iterator().next();
    }

    // Helper method to get last value from LinkedHashMap
    private <K, V> V getLastMapValue(LinkedHashMap<K, V> map) {
        if (map.isEmpty()) {
            return null;
        }
        V lastValue = null;
        for (V value : map.values()) {
            lastValue = value;
        }
        return lastValue;
    }

    // Demo 3: Processing recent activity feed with manual reverse iteration
    public void processRecentActivityFeed() {
        System.out.println("=== Demo 3: Recent Activity Feed with Manual Reverse ===");
        
        LinkedHashSet<String> activities = new LinkedHashSet<>();
        
        // Add activities (newest first in terms of insertion)
        activities.add("User logged in");
        activities.add("Order placed");
        activities.add("Payment processed");
        activities.add("Profile updated");
        activities.add("Product viewed");

        System.out.println("Original activities (newest first):");
        activities.forEach(System.out::println);

        System.out.println("\nReversed view (oldest first - manual):");
        // Manual reverse iteration
        List<String> reversedList = new ArrayList<>(activities);
        for (int i = reversedList.size() - 1; i >= 0; i--) {
            System.out.println(reversedList.get(i));
        }

        // Process activities from oldest to newest
        System.out.println("\nProcessing from oldest to newest (manual):");
        List<String> activitiesList = new ArrayList<>(activities);
        for (int i = activitiesList.size() - 1; i >= 0; i--) {
            String activity = activitiesList.get(i);
            System.out.println("Processing: " + activity);
        }

        System.out.println();
    }

    // Alternative Demo 3: Using Java 21 sequenced collections (if available)
    public void processRecentActivityFeedJava21() {
        System.out.println("=== Demo 3 Alternative: Using Java 21 Sequenced Collections ===");
        
        // This would work if we had access to Java 21's sequenced collections
        try {
            // Simulating what it would look like with Java 21
            LinkedHashSet<String> activities = new LinkedHashSet<>();
            activities.add("User logged in");
            activities.add("Order placed");
            activities.add("Payment processed");
            
            System.out.println("If using Java 21 sequenced collections, we could use:");
            System.out.println("activities.reversed().forEach(...)");
            System.out.println("But we need to use manual methods for now");
            
        } catch (Exception e) {
            System.out.println("Java 21 sequenced collections not available: " + e.getMessage());
        }
        System.out.println();
    }

    // Main demo method
    public static void main(String[] args) {
        SequencedCollectionsDemo demo = new SequencedCollectionsDemo();
        demo.demoLinkedHashSetBoundedCache();
        demo.demoPaginationWithSequencedMap();
        demo.processRecentActivityFeed();
        demo.processRecentActivityFeedJava21();
    }
}