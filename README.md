# Java 21 Features Demo 🚀

A comprehensive demonstration of Java 21's new features integrated with Spring Boot, designed for developers who want to learn practical applications of the latest Java capabilities.

## 📋 Table of Contents

- [Overview](#overview)
- [Features Covered](#features-covered)
- [Prerequisites](#prerequisites)
- [Quick Start](#quick-start)
- [Project Structure](#project-structure)
- [Feature Demonstrations](#feature-demonstrations)
- [Running the Demos](#running-the-demos)
- [Spring Boot Integration](#spring-boot-integration)
- [Course Materials](#course-materials)
- [Contributing](#contributing)
- [License](#license)

## 🎯 Overview

This repository contains practical demonstrations of Java 21's key features, showing how they can be applied in real-world business scenarios. Each feature is demonstrated through concrete examples that you might encounter in enterprise applications.

**Perfect for:**
- Java developers upgrading to Java 21
- Students learning modern Java features
- Teams evaluating Java 21 adoption
- Educators teaching Java 21 concepts

## ✨ Features Covered

### 🔸 Record Patterns
- **Payment Processing System** - Clean pattern matching for different payment types
- **Order Management** - Smart routing based on order characteristics
- **Fraud Detection** - Pattern-based suspicious activity detection

### 🔸 String Templates *(Preview Feature)*
- **Safe SQL Query Generation** - SQL injection prevention
- **JSON Template Processing** - Type-safe JSON construction
- **HTML Template Generation** - Dynamic content creation
- **Email Template System** - Parameterized email generation

### 🔸 Sequenced Collections
- **LRU Cache Implementation** - Efficient cache management
- **Pagination Support** - First/last element access for APIs
- **Activity Feed Processing** - Ordered data manipulation

### 🔸 Unnamed Classes & Main Methods
- **Quick Scripting** - Simplified utility scripts
- **Rapid Prototyping** - Fast concept validation
- **Educational Examples** - Beginner-friendly demos

### 🔸 Unnamed Patterns & Variables
- **Exception Handling** - Cleaner error management
- **Resource Management** - Simplified try-with-resources
- **Loop Processing** - Focus on business logic

## 📋 Prerequisites

- **Java 21 or higher** (with preview features enabled)
- **Maven 3.6+**
- **IDE with Java 21 support** (IntelliJ IDEA 2023.2+, Eclipse 2023-09+, VS Code with Java extensions)

## 🚀 Quick Start

### 1. Clone the Repository
```bash
git clone https://github.com/j2eeexpert2015/java-21-features-demo.git
cd java-21-features-demo
```

### 2. Build the Project
```bash
mvn clean compile
```

### 3. Run Individual Demos
```bash
# Record Patterns Demo
mvn exec:java -Dexec.mainClass="com.example.java21features.recordpatterns.RecordPatternsDemo"

# String Templates Demo
mvn exec:java -Dexec.mainClass="com.example.java21features.stringtemplates.StringTemplatesDemo"

# Sequenced Collections Demo
mvn exec:java -Dexec.mainClass="com.example.java21features.sequencedcollections.SequencedCollectionsDemo"
```

### 4. Run Spring Boot Application
```bash
mvn spring-boot:run
```

Then visit: http://localhost:8080/api/orders

## 📁 Project Structure

```
src/
├── main/java/com/example/
│   ├── java21features/
│   │   ├── recordpatterns/          # 🎯 Payment & Order Processing
│   │   │   ├── RecordPatternsDemo.java
│   │   │   └── model/
│   │   │       ├── Payment.java     # Sealed interface with records
│   │   │       └── Order.java       # Business logic methods
│   │   │
│   │   ├── stringtemplates/         # 📝 Safe Template Processing
│   │   │   ├── StringTemplatesDemo.java
│   │   │   ├── model/User.java
│   │   │   └── templateprocessors/
│   │   │       ├── JsonTemplateProcessor.java
│   │   │       └── SqlTemplateProcessor.java
│   │   │
│   │   ├── sequencedcollections/    # 📚 Ordered Collections
│   │   │   ├── SequencedCollectionsDemo.java
│   │   │   └── model/Order.java
│   │   │
│   │   ├── unnamedclasses/          # 🎪 Simplified Classes
│   │   │   ├── QuickUtilities.java
│   │   │   ├── RapidPrototyping.java
│   │   │   └── SimpleScripts.java
│   │   │
│   │   └── unnamedpatterns/         # 🎭 Cleaner Pattern Matching
│   │       └── UnnamedPatternsDemo.java
│   │
│   └── springapp/                   # 🌱 Spring Boot Integration
│       ├── controller/OrderController.java
│       ├── model/Order.java
│       └── service/
│           ├── OrderService.java
│           ├── PaymentService.java
│           ├── LoggingService.java
│           ├── ScriptingService.java
│           └── TemplateService.java
│
└── test/java/                       # 🧪 Unit Tests
    └── com/example/java21features/
```

## 🎪 Feature Demonstrations

### Record Patterns in Action

```java
// Clean payment processing with pattern matching
public void processPayment(Payment payment) {
    switch (payment) {
        case Payment.CreditCard(String id, double amount, String status, 
                               String lastFour, String cardType, boolean isInternational) -> {
            if (isInternational) {
                System.out.println("International fee applies: " + cardType);
            }
        }
        case Payment.PayPal(String id, double amount, String status, 
                           String email, boolean isBusiness) -> {
            String type = isBusiness ? "Business" : "Personal";
            System.out.println("PayPal payment: " + type);
        }
        // ... more patterns
    }
}
```

### String Templates for Safety

```java
// SQL injection prevention
SqlTemplateProcessor.SqlTemplate safeSql = SqlTemplateProcessor.of(
    "SELECT * FROM users WHERE username = ? AND status = ?",
    username, status
);
```

### Sequenced Collections for APIs

```java
// Efficient pagination with first/last metadata
public Map<String, Object> getPaginatedOrders(int page, int size) {
    // ... pagination logic
    response.put("firstItemId", orders.getFirst().getId());  // Java 21 style
    response.put("lastItemId", orders.getLast().getId());    // Java 21 style
    return response;
}
```

## 🏃‍♂️ Running the Demos

### Individual Feature Demos

Each demo is self-contained and can be run independently:

```bash
# 1. Record Patterns - Payment Processing System
mvn exec:java -Dexec.mainClass="com.example.java21features.recordpatterns.RecordPatternsDemo"

# 2. String Templates - Safe Template Processing
mvn exec:java -Dexec.mainClass="com.example.java21features.stringtemplates.StringTemplatesDemo"

# 3. Sequenced Collections - LRU Cache & Pagination
mvn exec:java -Dexec.mainClass="com.example.java21features.sequencedcollections.SequencedCollectionsDemo"

# 4. Unnamed Patterns - Cleaner Exception Handling
mvn exec:java -Dexec.mainClass="com.example.java21features.unnamedpatterns.UnnamedPatternsDemo"

# 5. Quick Utilities - Scripting Style
mvn exec:java -Dexec.mainClass="com.example.java21features.unnamedclasses.QuickUtilities"
```

### Spring Boot REST API

```bash
# Start the application
mvn spring-boot:run

# Test the endpoints
curl http://localhost:8080/api/orders
curl http://localhost:8080/api/orders?page=1&size=5
curl http://localhost:8080/api/orders/1
```

### Running Tests

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=RecordPatternsDemoTest
```

## 🌱 Spring Boot Integration

The project demonstrates how Java 21 features integrate seamlessly with Spring Boot:

### RESTful APIs with Pattern Matching
```java
@RestController
public class OrderController {
    @GetMapping("/api/orders")
    public ResponseEntity<Map<String, Object>> getOrders(
            @RequestParam(defaultValue = "1") int page) {
        // Uses sequenced collections for pagination
        return ResponseEntity.ok(orderService.getOrdersWithPagination(page, 10));
    }
}
```

### Service Layer with Record Patterns
```java
@Service
public class PaymentService {
    public String processPayment(Payment payment) {
        return switch (payment) {
            case Payment.CreditCard(var id, var amount, var status, 
                                   var lastFour, var cardType, var isInternational) -> 
                String.format("Processed CC %s: $%.2f", id, amount);
            // ... other payment types
        };
    }
}
```

## 📚 Course Materials

### Learning Path

1. **Start Here**: `RecordPatternsDemo.java` - Most practical and immediately useful
2. **Safety Focus**: `StringTemplatesDemo.java` - Security-conscious templating
3. **Collections**: `SequencedCollectionsDemo.java` - Better APIs and caching
4. **Simplification**: `UnnamedClassesDemo.java` - Reduced boilerplate
5. **Clean Code**: `UnnamedPatternsDemo.java` - Focused exception handling

### Key Learning Objectives

- ✅ Understand when and how to use record patterns
- ✅ Implement safe string templating to prevent injection attacks
- ✅ Leverage sequenced collections for better API design
- ✅ Use unnamed classes for quick scripting and prototyping
- ✅ Apply unnamed patterns for cleaner exception handling

### Hands-On Exercises

1. **Payment Processor**: Extend the payment system with new payment types
2. **Template Security**: Create your own template processor with validation
3. **Cache Implementation**: Build an LRU cache using sequenced collections
4. **Quick Scripts**: Convert verbose utility classes to unnamed classes
5. **Error Handling**: Refactor existing code to use unnamed patterns

## 🔧 IDE Setup

### IntelliJ IDEA
1. Enable preview features: Settings → Build → Compiler → Java Compiler
2. Add `--enable-preview` to VM options
3. Set language level to "21 (Preview)"

### Eclipse
1. Project Properties → Java Build Path → Compiler Compliance Level: 21
2. Enable preview features in project settings
3. Add `--enable-preview` to launch configurations

### VS Code
1. Install "Extension Pack for Java"
2. Configure Java runtime to Java 21
3. Add `--enable-preview` to launch.json

## ⚠️ Important Notes

### Preview Features
String Templates are **preview features** in Java 21. The syntax shown in this demo represents the expected final form, but may require updates as the feature evolves.


---

**Happy Learning! 🎓**

*This demo repository is part of the "What's New in Java 21" course series. For more advanced Java topics, check out our other courses on Virtual Threads, Performance Optimization, and Modern Java Development.*