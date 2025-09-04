package com.example.springapp.service;

import com.example.java21features.recordpatterns.model.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    // Process payment based on type - clean pattern matching
    public String processPayment(Payment payment) {
        return switch (payment) {
            case Payment.CreditCard(var id, var amount, var status, 
                                  var lastFour, var cardType, var isInternational) -> 
                String.format("Processed CC %s: $%.2f via %s", id, amount, cardType);
            
            case Payment.PayPal(var id, var amount, var status, 
                              var email, var isBusiness) -> 
                String.format("Processed PayPal %s: $%.2f from %s", id, amount, email);
            
            case Payment.BankTransfer(var id, var amount, var status, 
                                    var bank, var reference) -> 
                String.format("Processing bank transfer %s: $%.2f", id, amount);
        };
    }

    // Check if payment needs manual review
    public boolean needsReview(Payment payment) {
        return switch (payment) {
            case Payment.CreditCard(var id, var amount, var status, 
                                  var lastFour, var cardType, var isInternational) -> 
                isInternational || amount > 1000.0;
            
            case Payment.PayPal(var id, var amount, var status, 
                              var email, var isBusiness) -> 
                !isBusiness && amount > 1500.0;
            
            case Payment.BankTransfer(var id, var amount, var status, 
                                    var bank, var reference) -> 
                amount > 3000.0;
        };
    }
}