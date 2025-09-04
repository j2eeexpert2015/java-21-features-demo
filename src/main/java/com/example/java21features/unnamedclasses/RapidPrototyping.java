package com.example.java21features.unnamedclasses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// Traditional prototyping class
public class RapidPrototyping {
    
    public static void main(String[] args) {
        System.out.println("=== RAPID PROTOTYPING DEMO ===");
        
        // Quick API test simulation
        testApiEndpoint();
        
        // Data transformation prototype
        transformData();
        
        // Algorithm experiment
        experimentWithAlgorithm();
    }
    
    static void testApiEndpoint() {
        System.out.println("Testing API endpoint...");
        
        // Simulate API response
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", List.of("item1", "item2", "item3"));
        response.put("timestamp", System.currentTimeMillis());
        
        System.out.println("API Response: " + response);
        System.out.println("Status: " + response.get("status"));
    }
    
    static void transformData() {
        System.out.println("\nTransforming data...");
        
        var inputData = List.of(
            Map.of("name", "Alice", "age", 30, "city", "New York"),
            Map.of("name", "Bob", "age", 25, "city", "London"),
            Map.of("name", "Charlie", "age", 35, "city", "Tokyo")
        );
        
        var transformed = inputData.stream()
            .filter(person -> (Integer)person.get("age") > 25)
            .map(person -> Map.of(
                "username", person.get("name").toString().toLowerCase(),
                "location", person.get("city"),
                "ageGroup", (Integer)person.get("age") > 30 ? "30+" : "20-30"
            ))
            .toList();
            
        System.out.println("Transformed data: " + transformed);
    }
    
    static void experimentWithAlgorithm() {
        System.out.println("\nAlgorithm experiment...");
        
        // Simple sorting experiment
        var numbers = new ArrayList<>(List.of(5, 2, 8, 1, 9, 3));
        System.out.println("Original: " + numbers);
        
        // Bubble sort for demonstration
        for (int i = 0; i < numbers.size() - 1; i++) {
            for (int j = 0; j < numbers.size() - i - 1; j++) {
                if (numbers.get(j) > numbers.get(j + 1)) {
                    int temp = numbers.get(j);
                    numbers.set(j, numbers.get(j + 1));
                    numbers.set(j + 1, temp);
                }
            }
        }
        
        System.out.println("Sorted: " + numbers);
    }
}

// Java 21 style rapid prototyping concept
class QuickPrototype {
    
    void main() {
        System.out.println("=== QUICK PROTOTYPE ===");
        
        // Rapid JSON-like structure
        var user = Map.of(
            "id", 123,
            "name", "Test User",
            "roles", List.of("admin", "user"),
            "metadata", Map.of("created", "2024-01-01", "active", true)
        );
        
        System.out.println("User prototype: " + user);
        
        // Quick validation
        boolean isValid = user.containsKey("name") && 
                         user.containsKey("id") && 
                         ((List<?>)user.get("roles")).contains("user");
                         
        System.out.println("Validation: " + (isValid ? "PASS" : "FAIL"));
        
        // Quick transformation
        var simplifiedUser = Map.of(
            "userId", user.get("id"),
            "userName", user.get("name"),
            "isActive", ((Map<?, ?>)user.get("metadata")).get("active")
        );
        
        System.out.println("Simplified: " + simplifiedUser);
    }
}