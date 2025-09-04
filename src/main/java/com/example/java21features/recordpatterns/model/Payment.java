package com.example.java21features.recordpatterns.model;

// Main payment sealed interface with all implementations in one file
public sealed interface Payment {
    String id();
    double amount();
    String status();
    
    // Credit card payment - most common type
    record CreditCard(String id, double amount, String status, 
                     String lastFour, String cardType, boolean isInternational) implements Payment {}
    
    // PayPal payment - digital wallet
    record PayPal(String id, double amount, String status, 
                  String email, boolean isBusiness) implements Payment {}
    
    // Bank transfer - for large amounts
    record BankTransfer(String id, double amount, String status, 
                        String bank, String reference) implements Payment {}
}