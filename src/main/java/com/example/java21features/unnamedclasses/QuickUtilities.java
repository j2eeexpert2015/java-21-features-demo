package com.example.java21features.unnamedclasses;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

// Traditional utility class
public class QuickUtilities {
    
    public static void main(String[] args) {
        if (args.length > 0) {
            processArguments(args);
        } else {
            demoUtilities();
        }
    }
    
    static void processArguments(String[] args) {
        System.out.println("Processing " + args.length + " arguments:");
        Arrays.stream(args).forEach(arg -> 
            System.out.println(" - " + arg)
        );
    }
    
    static void demoUtilities() {
        System.out.println("=== QUICK UTILITIES DEMO ===");
        
        // String operations
        String text = "Hello, Java 21!";
        System.out.println("Original: " + text);
        System.out.println("Reversed: " + reverseString(text));
        System.out.println("Word count: " + countWords(text));
        
        // Number operations
        System.out.println("Sum 1-10: " + sumRange(1, 10));
        System.out.println("Is 17 prime? " + isPrime(17));
    }
    
    static String reverseString(String input) {
        return new StringBuilder(input).reverse().toString();
    }
    
    static int countWords(String text) {
        return text.split("\\s+").length;
    }
    
    static int sumRange(int start, int end) {
        int sum = 0;
        for (int i = start; i <= end; i++) {
            sum += i;
        }
        return sum;
    }
    
    static boolean isPrime(int number) {
        if (number <= 1) return false;
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) return false;
        }
        return true;
    }
}

// Java 21 style - conceptual example
class ScriptingStyle {
    
    // Simplified main method
    void main() {
        System.out.println("=== SCRIPTING-STYLE UTILITIES ===");
        
        // Quick data processing
        var numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        var evenSquares = numbers.stream()
            .filter(n -> n % 2 == 0)
            .map(n -> n * n)
            .toList();
            
        System.out.println("Even squares: " + evenSquares);
        
        // Quick file operation simulation
        try {
            var tempFile = java.nio.file.Path.of("temp.txt");
            var content = "Generated at: " + java.time.LocalDateTime.now();
            System.out.println("Would write: " + content);
        } catch (Exception e) {
            System.out.println("File operation simulated");
        }
    }
}