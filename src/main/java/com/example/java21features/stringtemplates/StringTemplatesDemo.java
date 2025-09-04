package com.example.java21features.stringtemplates;

import com.example.java21features.stringtemplates.model.User;
import com.example.java21features.stringtemplates.templateprocessors.JsonTemplateProcessor;
import com.example.java21features.stringtemplates.templateprocessors.SqlTemplateProcessor;

import java.util.List;
import java.util.Map;

public class StringTemplatesDemo {

    // Demo 1: Basic String Interpolation
    public void demoBasicInterpolation() {
        System.out.println("=== Demo 1: Basic String Interpolation ===");
        
        String name = "Alice";
        int age = 30;
        double score = 95.5;
        
        // Traditional way (verbose)
        String traditional = "Name: " + name + ", Age: " + age + ", Score: " + score;
        System.out.println("Traditional: " + traditional);
        
        // String.format (better but still not ideal)
        String formatted = String.format("Name: %s, Age: %d, Score: %.1f", name, age, score);
        System.out.println("Formatted: " + formatted);
        
        // Java 21 String Template (conceptual - using manual implementation)
        String template = interpolate("Name: {0}, Age: {1}, Score: {2}", name, age, score);
        System.out.println("Template: " + template);
        
        System.out.println();
    }

    // Demo 2: Safe SQL Query Generation
    public void demoSafeSqlQueries() {
        System.out.println("=== Demo 2: Safe SQL Query Generation ===");
        
        String username = "alice";
        String status = "active";
        int minAge = 25;
        
        // UNSAFE approach (vulnerable to SQL injection)
        String unsafeSql = "SELECT * FROM users WHERE username = '" + username + 
                          "' AND status = '" + status + "' AND age > " + minAge;
        System.out.println("Unsafe SQL: " + unsafeSql);
        System.out.println("⚠️  Vulnerable to SQL injection!");
        
        // SAFE approach using our template processor
        try {
            SqlTemplateProcessor.SqlTemplate safeSql = SqlTemplateProcessor.of(
                "SELECT * FROM users WHERE username = ? AND status = ? AND age > ?",
                username, status, minAge
            );
            
            System.out.println("Safe SQL: " + safeSql.getQuery());
            System.out.println("Parameters: " + safeSql.getParameters());
            System.out.println("✅ Protected against SQL injection");
            
        } catch (Exception e) {
            System.out.println("SQL validation error: " + e.getMessage());
        }
        
        System.out.println();
    }

    // Demo 3: JSON Generation with Validation
    public void demoJsonGeneration() {
        System.out.println("=== Demo 3: JSON Generation with Validation ===");
        
        User user = new User(1L, "Bob", "bob@example.com", 28);
        
        // Manual JSON construction (error-prone)
        String manualJson = "{\"id\": " + user.getId() + 
                           ", \"name\": \"" + user.getName() + "\"" +
                           ", \"email\": \"" + user.getEmail() + "\"" +
                           ", \"age\": " + user.getAge() + "}";
        System.out.println("Manual JSON: " + manualJson);
        
        // Using JSON template processor
        try {
            JsonTemplateProcessor.JsonTemplate jsonTemplate = JsonTemplateProcessor.of(
                "{\"id\": {0}, \"name\": {1}, \"email\": {2}, \"age\": {3}}",
                user.getId(), user.getName(), user.getEmail(), user.getAge()
            );
            
            System.out.println("Template JSON: " + jsonTemplate.getJsonString());
            System.out.println("Valid JSON: " + jsonTemplate.isValid());
            System.out.println("JSON Node: " + jsonTemplate.getJsonNode());
            
        } catch (Exception e) {
            System.out.println("JSON error: " + e.getMessage());
        }
        
        System.out.println();
    }

    // Demo 4: Complex Templates with Collections
    public void demoComplexTemplates() {
        System.out.println("=== Demo 4: Complex Templates with Collections ===");
        
        List<String> skills = List.of("Java", "Spring", "SQL");
        Map<String, Object> userData = Map.of(
            "username", "charlie",
            "premium", true,
            "score", 88.7
        );
        
        // Complex template with collections
        String template = interpolate(
            "User: {0} | Premium: {1} | Score: {2} | Skills: {3}",
            userData.get("username"), userData.get("premium"), 
            userData.get("score"), String.join(", ", skills)
        );
        
        System.out.println("Complex template: " + template);
        System.out.println();
    }

    // Demo 5: HTML Template Generation
    public void demoHtmlTemplates() {
        System.out.println("=== Demo 5: HTML Template Generation ===");
        
        String title = "User Profile";
        String username = "diana";
        String role = "Admin";
        
        String html = interpolate("""
            <div class="profile">
                <h1>{0}</h1>
                <p>Username: {1}</p>
                <p>Role: {2}</p>
            </div>
            """, title, username, role);
        
        System.out.println("HTML Template:");
        System.out.println(html);
        System.out.println();
    }

    // Manual interpolation method (simulating string templates)
    private String interpolate(String template, Object... values) {
        String result = template;
        for (int i = 0; i < values.length; i++) {
            String placeholder = "\\{" + i + "\\}";
            result = result.replaceAll(placeholder, 
                values[i] != null ? values[i].toString() : "null");
        }
        return result;
    }

    // Main demo method
    public static void main(String[] args) {
        StringTemplatesDemo demo = new StringTemplatesDemo();
        demo.demoBasicInterpolation();
        demo.demoSafeSqlQueries();
        demo.demoJsonGeneration();
        demo.demoComplexTemplates();
        demo.demoHtmlTemplates();
        
        System.out.println("=== Real Java 21 String Templates would look like: ===");
        System.out.println("String name = \\\"Alice\\\";");
        System.out.println("String message = STR.\\\"Hello, \\\\{name}!\\\";");
        System.out.println("// message = \\\"Hello, Alice!\\\"");
    }
}