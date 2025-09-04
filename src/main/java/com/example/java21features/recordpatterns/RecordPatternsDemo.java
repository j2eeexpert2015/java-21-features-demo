package com.example.java21features.recordpatterns;

import com.example.java21features.recordpatterns.model.*;

import java.time.LocalDateTime;
import java.util.List;

public class RecordPatternsDemo {

    public static void main(String[] args) {
        System.out.println("=== PRACTICAL RECORD PATTERNS DEMO ===\n");
        
        // Create sample data
        List<Payment> payments = List.of(
            new Payment.CreditCard("pay1", 299.99, "COMPLETED", "1234", "VISA", false),
            new Payment.PayPal("pay2", 1200.00, "COMPLETED", "biz@company.com", true),
            new Payment.BankTransfer("pay3", 5000.00, "PENDING", "Chase", "INV-001"),
            new Payment.CreditCard("pay4", 89.99, "FAILED", "4321", "MC", true)
        );
        
        List<Order> orders = List.of(
            new Order("order1", "John Doe", List.of("iPhone", "Case"), 1099.99, 
                     LocalDateTime.now(), "PAID", "PREMIUM"),
            new Order("order2", "Jane Smith", List.of("Book", "Pen"), 45.99, 
                     LocalDateTime.now(), "PENDING", "BASIC"),
            new Order("order3", "VIP Customer", List.of("MacBook", "Mouse"), 2499.99, 
                     LocalDateTime.now(), "PAID", "VIP")
        );
        
        // Demo 1: Process payments with pattern matching
        System.out.println("1. PAYMENT PROCESSING:");
        processPayments(payments);
        
        // Demo 2: Handle orders based on status and value
        System.out.println("\n2. ORDER FULFILLMENT:");
        processOrders(orders);
        
        // Demo 3: Fraud detection
        System.out.println("\n3. FRAUD DETECTION:");
        detectSuspiciousActivity(payments);
        
        // Demo 4: Fee calculation
        System.out.println("\n4. FEE CALCULATION:");
        calculateFees(payments);
    }
    
    // Process different payment types with clean pattern matching
    private static void processPayments(List<Payment> payments) {
        for (Payment payment : payments) {
            switch (payment) {
                case Payment.CreditCard(String id, double amount, String status, 
                                      String lastFour, String cardType, boolean isInternational) -> {
                    
                    String base = String.format("Credit Card %s: $%.2f via %s ****%s", 
                                              id, amount, cardType, lastFour);
                    if (isInternational) {
                        System.out.println("   " + base + " 🌍 (Int'l fee applies)");
                    } else if ("FAILED".equals(status)) {
                        System.out.println("   " + base + " ❌ (Retry needed)");
                    } else {
                        System.out.println("   " + base + " ✅");
                    }
                }
                
                case Payment.PayPal(String id, double amount, String status, 
                                   String email, boolean isBusiness) -> {
                    
                    String accountType = isBusiness ? "Biz" : "Personal";
                    String statusIcon = "COMPLETED".equals(status) ? "✅" : "⏳";
                    System.out.println("   PayPal " + id + ": $" + amount + 
                                     " from " + email + " (" + accountType + ") " + statusIcon);
                }
                
                case Payment.BankTransfer(String id, double amount, String status, 
                                         String bank, String reference) -> {
                    
                    String statusIcon = "PENDING".equals(status) ? "⏳" : "✅";
                    System.out.println("   Bank Transfer " + id + ": $" + amount + 
                                     " from " + bank + " Ref: " + reference + " " + statusIcon);
                }
            }
        }
    }
    
    // Smart order routing based on order characteristics
    private static void processOrders(List<Order> orders) {
        for (Order order : orders) {
            switch (order.status()) {
                case "PAID" -> {
                    if (order.isPriority()) {
                        System.out.println("   Order " + order.id() + ": 🚀 Express shipping for VIP");
                    } else if (order.containsElectronics()) {
                        System.out.println("   Order " + order.id() + ": 📦 Signature required for electronics");
                    } else if (order.isHighValue()) {
                        System.out.println("   Order " + order.id() + ": 🔒 Insurance added for high value");
                    } else {
                        System.out.println("   Order " + order.id() + ": 📮 Standard shipping");
                    }
                }
                case "PENDING" -> System.out.println("   Order " + order.id() + ": ⏳ Waiting for payment");
                case "SHIPPED" -> System.out.println("   Order " + order.id() + ": 📤 Out for delivery");
                case "DELIVERED" -> System.out.println("   Order " + order.id() + ": ✅ Order completed");
                default -> System.out.println("   Order " + order.id() + ": ❓ Unknown status");
            }
        }
    }
    
    // Fraud detection using pattern matching
    private static void detectSuspiciousActivity(List<Payment> payments) {
        for (Payment payment : payments) {
            boolean isSuspicious = switch (payment) {
                case Payment.CreditCard(var id, var amount, var status, 
                                       var lastFour, var cardType, var isInternational) -> 
                    isInternational && amount > 1000.0;
                
                case Payment.PayPal(var id, var amount, var status, 
                                   var email, var isBusiness) -> 
                    !isBusiness && amount > 2000.0;
                
                case Payment.BankTransfer(var id, var amount, var status, 
                                         var bank, var reference) -> 
                    amount > 5000.0;
            };
            
            if (isSuspicious) {
                System.out.println("   🚨 Suspicious: " + payment.id() + " ($" + payment.amount() + ")");
            } else {
                System.out.println("   ✅ Clean: " + payment.id());
            }
        }
    }
    
    // Calculate and display fees
    private static void calculateFees(List<Payment> payments) {
        for (Payment payment : payments) {
            double fee = switch (payment) {
                case Payment.CreditCard(var id, var amount, var status, 
                                       var lastFour, var cardType, var isInternational) -> 
                    isInternational ? amount * 0.03 : amount * 0.02;
                
                case Payment.PayPal(var id, var amount, var status, 
                                   var email, var isBusiness) -> 
                    isBusiness ? amount * 0.029 : amount * 0.034;
                
                case Payment.BankTransfer(var id, var amount, var status, 
                                         var bank, var reference) -> 
                    5.00; // Flat fee
            };
            
            System.out.println("   Fee for " + payment.id() + ": $" + String.format("%.2f", fee));
        }
    }
}