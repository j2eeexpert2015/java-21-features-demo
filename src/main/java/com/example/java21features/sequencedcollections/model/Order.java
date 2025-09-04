package com.example.java21features.sequencedcollections.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Order {
    private final Long id;
    private final String customerName;
    private final Double amount;
    private final LocalDateTime createdAt;
    private final String status;

    public Order(Long id, String customerName, Double amount, LocalDateTime createdAt, String status) {
        this.id = id;
        this.customerName = customerName;
        this.amount = amount;
        this.createdAt = createdAt;
        this.status = status;
    }

    // Getters
    public Long getId() { return id; }
    public String getCustomerName() { return customerName; }
    public Double getAmount() { return amount; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public String getStatus() { return status; }

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