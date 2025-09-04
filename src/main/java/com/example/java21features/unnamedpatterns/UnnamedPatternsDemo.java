package com.example.java21features.unnamedpatterns;

import com.example.java21features.recordpatterns.model.Payment;
import com.example.java21features.recordpatterns.model.Order;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class UnnamedPatternsDemo {

    public static void main(String[] args) {
        System.out.println("=== PRACTICAL UNNAMED PATTERNS & VARIABLES CONCEPTS ===\n");
        
        // Create sample data
        List<Payment> payments = List.of(
            new Payment.CreditCard("pay1", 299.99, "COMPLETED", "1234", "VISA", false),
            new Payment.PayPal("pay2", 1200.00, "COMPLETED", "biz@company.com", true),
            new Payment.BankTransfer("pay3", 5000.00, "PENDING", "Chase", "INV-001")
        );
        
        List<Order> orders = List.of(
            new Order("order1", "John Doe", List.of("iPhone", "Case"), 1099.99, 
                     LocalDateTime.now(), "PAID", "PREMIUM"),
            new Order("order2", "Jane Smith", List.of("Book", "Pen"), 45.99, 
                     LocalDateTime.now(), "PENDING", "BASIC")
        );

        // Demo 1: Exception handling concepts
        System.out.println("1. EXCEPTION HANDLING (Unused Variables):");
        demoExceptionHandling();

        // Demo 2: Pattern matching with focused field extraction
        System.out.println("\n2. PATTERN MATCHING (Focused Field Extraction):");
        demoPatternMatchingWithFocusedFields(payments);

        // Demo 3: Loop processing without element usage
        System.out.println("\n3. LOOP PROCESSING (Count-only Operations):");
        demoCountOnlyLoops(orders);

        // Demo 4: Resource management
        System.out.println("\n4. RESOURCE MANAGEMENT:");
        demoTryWithResources();

        // Demo 5: Optional processing
        System.out.println("\n5. OPTIONAL PROCESSING:");
        demoOptionalHandling();
    }

    // Demo 1: Exception handling where we don't need exception details
    private static void demoExceptionHandling() {
        // Traditional approach with unused variable
        try {
            int result = 10 / 0;
        } catch (ArithmeticException e) {
            // e is unused but we have to declare it
            System.out.println("   Arithmetic error occurred");
        }

        // Concept: In Java 21, we could potentially ignore the variable
        System.out.println("   Concept: Java 21 allows ignoring exception variables");
    }

    // Demo 2: Pattern matching - extracting only what we need
    private static void demoPatternMatchingWithFocusedFields(List<Payment> payments) {
        for (Payment payment : payments) {
            // Extract only the fields we actually need
            switch (payment) {
                case Payment.CreditCard(String id, double amount, String status, 
                                      String lastFour, String cardType, boolean isInternational) -> {
                    // We only use amount and cardType, others are unused but required
                    System.out.println("   Credit Card: $" + amount + " via " + cardType);
                }
                
                case Payment.PayPal(String id, double amount, String status, 
                                   String email, boolean isBusiness) -> {
                    // Focus on amount and business status
                    String type = isBusiness ? "Business" : "Personal";
                    System.out.println("   PayPal: $" + amount + " (" + type + ")");
                }
                
                case Payment.BankTransfer(String id, double amount, String status, 
                                         String bank, String reference) -> {
                    // Only need amount and bank
                    System.out.println("   Bank Transfer: $" + amount + " from " + bank);
                }
            }
        }
    }

    // Demo 3: Loops where we only need the count, not individual elements
    private static void demoCountOnlyLoops(List<Order> orders) {
        // Counting orders without using the order object
        int highValueCount = 0;
        int premiumCount = 0;
        
        for (Order order : orders) {
            if (order.isHighValue()) {
                highValueCount++;
            }
            if (order.isPriority()) {
                premiumCount++;
            }
        }
        
        System.out.println("   High-value orders: " + highValueCount);
        System.out.println("   Premium orders: " + premiumCount);
        
        // Concept: Sometimes we just need to iterate a fixed number of times
        for (int i = 0; i < 3; i++) {
            System.out.println("   Processing batch operation " + (i + 1));
        }
    }

    // Demo 4: Try-with-resources where resource is managed but not used
    private static void demoTryWithResources() {
        // Resource is acquired and managed but not used in the block
        try (var resource = new SimpleResource()) {
            System.out.println("   Resource acquired and managed automatically");
            // We don't use the resource variable in this block
        }
    }

    // Demo 5: Optional handling where presence matters, not value
    private static void demoOptionalHandling() {
        Optional<String> customerEmail = Optional.of("customer@example.com");
        Optional<String> emptyOptional = Optional.empty();
        
        // Check presence without using the value
        if (customerEmail.isPresent()) {
            System.out.println("   Customer email is available");
        }
        
        if (emptyOptional.isEmpty()) {
            System.out.println("   No secondary email available");
        }
        
        // Execute action if present, without using the value
        customerEmail.ifPresent(value -> {
            System.out.println("   Email verification sent");
            // value is available but we don't use it
        });
    }

    // Simple resource class
    static class SimpleResource implements AutoCloseable {
        @Override
        public void close() {
            System.out.println("   Resource closed successfully");
        }
    }
}