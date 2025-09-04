package com.example.springapp.service;

import com.example.java21features.stringtemplates.templateprocessors.JsonTemplateProcessor;
import com.example.java21features.stringtemplates.templateprocessors.SqlTemplateProcessor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TemplateService {

    public String generateUserWelcomeEmail(String username, String fullName) {
        // Simulating string template for email content
        return interpolate("""
            Dear {0},
            
            Welcome to our service! Your username is: {1}
            
            We're excited to have you on board.
            
            Best regards,
            The Team
            """, fullName, username);
    }

    public String generateApiResponse(String status, String message, Object data) {
        // Using JSON template for API responses
        JsonTemplateProcessor.JsonTemplate json = JsonTemplateProcessor.of(
            "{\"status\": \"{0}\", \"message\": \"{1}\", \"data\": {2}, \"timestamp\": {3}}",
            status, message, data != null ? data.toString() : "null", System.currentTimeMillis()
        );
        
        return json.getJsonString();
    }

    public SqlTemplateProcessor.SqlTemplate createUserSearchQuery(
            String username, String email, Integer minAge, Boolean active) {
        
        StringBuilder query = new StringBuilder("SELECT * FROM users WHERE 1=1");
        List<Object> params = new ArrayList<>();
        
        if (username != null) {
            query.append(" AND username = ?");
            params.add(username);
        }
        if (email != null) {
            query.append(" AND email = ?");
            params.add(email);
        }
        if (minAge != null) {
            query.append(" AND age >= ?");
            params.add(minAge);
        }
        if (active != null) {
            query.append(" AND active = ?");
            params.add(active);
        }
        
        return SqlTemplateProcessor.of(query.toString(), params.toArray());
    }

    private String interpolate(String template, Object... values) {
        String result = template;
        for (int i = 0; i < values.length; i++) {
            String placeholder = "\\{" + i + "\\}";
            result = result.replaceAll(placeholder, 
                values[i] != null ? values[i].toString() : "null");
        }
        return result;
    }
}