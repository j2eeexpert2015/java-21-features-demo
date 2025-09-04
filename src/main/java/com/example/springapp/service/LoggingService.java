package com.example.springapp.service;

import com.example.java21features.recordpatterns.model.Payment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoggingService {

    // Process payments but only log aggregate information
    public void processPaymentBatch(List<Payment> payments) {
        int processedCount = 0;
        double totalAmount = 0;
        
        for (Payment payment : payments) {
            // We process each payment but only care about aggregates
            processedCount++;
            totalAmount += payment.amount();
        }
        
        System.out.println("Processed " + processedCount + " payments totaling $" + totalAmount);
    }

    // Exception handling where we don't need exception details
    public String handleSafely(String input) {
        try {
            return processInput(input);
        } catch (IllegalArgumentException e) {
            // We don't use the exception details, just handle the case
            return "Invalid input provided";
        } catch (RuntimeException e) {
            // Another case where we don't need exception details
            return "Processing error occurred";
        }
    }
    
    private String processInput(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("Empty input");
        }
        return input.trim().toUpperCase();
    }

    // Pattern matching with focused field usage
    public void auditPayment(Payment payment) {
        switch (payment) {
            case Payment.CreditCard(String id, double amount, String status, 
                                  String lastFour, String cardType, boolean isInternational) -> {
                // Audit log - we only care about certain fields
                System.out.println("Credit card audit: " + cardType + " amount: " + amount);
            }
            
            case Payment.PayPal(String id, double amount, String status, 
                               String email, boolean isBusiness) -> {
                // Business payments get different auditing
                if (isBusiness) {
                    System.out.println("Business PayPal payment: " + amount);
                }
            }
            
            case Payment.BankTransfer(String id, double amount, String status, 
                                     String bank, String reference) -> {
                // Large transfers get special attention
                if (amount > 10000) {
                    System.out.println("Large bank transfer: " + bank + " amount: " + amount);
                }
            }
        }
    }

    // Optional processing where we only care about presence
    public void handleOptionalData(Optional<String> data) {
        if (data.isPresent()) {
            System.out.println("Data is available for processing");
            // We might not need the actual value here
        } else {
            System.out.println("No data available - using defaults");
        }
    }

    // Additional practical methods demonstrating the concepts

    // Process multiple payments but only check for specific conditions
    public int countInternationalPayments(List<Payment> payments) {
        int internationalCount = 0;
        
        for (Payment payment : payments) {
            if (isInternationalPayment(payment)) {
                internationalCount++;
            }
        }
        
        return internationalCount;
    }

    private boolean isInternationalPayment(Payment payment) {
        return switch (payment) {
            case Payment.CreditCard(String id, double amount, String status, 
                                  String lastFour, String cardType, boolean isInternational) -> 
                isInternational;
            
            case Payment.PayPal(String id, double amount, String status, 
                               String email, boolean isBusiness) -> 
                email != null && email.contains(".uk"); // Example: UK-based emails
            
            case Payment.BankTransfer(String id, double amount, String status, 
                                     String bank, String reference) -> 
                bank != null && !bank.contains("USA"); // Example: non-US banks
        };
    }

    // Handle payment without needing all details
    public String getPaymentSummary(Payment payment) {
        return switch (payment) {
            case Payment.CreditCard(var id, var amount, var status, 
                                  var lastFour, var cardType, var isInternational) -> 
                String.format("Credit Card: %s ending %s", cardType, lastFour);
            
            case Payment.PayPal(var id, var amount, var status, 
                               var email, var isBusiness) -> 
                String.format("PayPal: %s", isBusiness ? "Business" : "Personal");
            
            case Payment.BankTransfer(var id, var amount, var status, 
                                     var bank, var reference) -> 
                String.format("Bank Transfer: %s", bank);
        };
    }

    // Process with fallback - don't need exception details
    public double calculateSafeTotal(List<Payment> payments) {
        double total = 0;
        
        for (Payment payment : payments) {
            try {
                total += payment.amount();
            } catch (Exception e) {
                // Don't need exception details, just skip invalid payments
                System.out.println("Skipping invalid payment");
            }
        }
        
        return total;
    }
}