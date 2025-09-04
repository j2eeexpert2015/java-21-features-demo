package com.example.java21features.unnamedclasses;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;

// Traditional Java class - verbose for simple scripts
public class SimpleScripts {
    
    public static void main(String[] args) {
        System.out.println("=== TRADITIONAL JAVA APPROACH ===");
        processData();
        generateReport();
    }
    
    static void processData() {
        List<String> names = List.of("Alice", "Bob", "Charlie");
        System.out.println("Processing " + names.size() + " names:");
        names.forEach(System.out::println);
    }
    
    static void generateReport() {
        System.out.println("Report generated at: " + LocalDateTime.now());
        System.out.println("Status: COMPLETED");
    }
}

// Java 21 Unnamed Class concept demonstration
// Note: This would be in a separate file without class declaration for real usage
class QuickDataProcessor {
    
    // Java 21 style: void main() without String[] args
    void main() {
        System.out.println("=== JAVA 21 UNNAMED CLASS APPROACH ===");
        
        List<String> data = List.of("Apple", "Banana", "Cherry");
        System.out.println("Data count: " + data.size());
        
        // Simple data processing
        data.stream()
            .filter(item -> item.startsWith("A"))
            .forEach(item -> System.out.println("Filtered: " + item));
            
        System.out.println("Processing completed at: " + java.time.LocalTime.now());
    }
}