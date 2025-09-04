package com.example.springapp.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Order {
    private Long id;
    private String customerName;
    private Double amount;
    private LocalDateTime createdAt;
    private String status;

    // Default constructor
    public Order() {}

    // All-args constructor
    public Order(Long id, String customerName, Double amount, LocalDateTime createdAt, String status) {
        this.id = id;
        this.customerName = customerName;
        this.amount = amount;
        this.createdAt = createdAt;
        this.status = status;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    // equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    // toString
    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                ", amount=" + amount +
                ", createdAt=" + createdAt +
                ", status='" + status + '\'' +
                '}';
    }
}