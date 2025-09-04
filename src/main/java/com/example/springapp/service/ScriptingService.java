package com.example.springapp.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ScriptingService {

    // Method that demonstrates scripting-style operations
    public String executeQuickScript(String scriptType, Map<String, Object> parameters) {
        return switch (scriptType) {
            case "data-summary" -> generateDataSummary(parameters);
            case "file-report" -> generateFileReport(parameters);
            case "validation-check" -> performValidationCheck(parameters);
            default -> "Unknown script type: " + scriptType;
        };
    }

    private String generateDataSummary(Map<String, Object> params) {
        try {
            var data = (List<?>) params.get("data");
            var summary = Map.of(
                "count", data.size(),
                "timestamp", LocalDateTime.now(),
                "type", params.get("dataType"),
                "processed", true
            );
            
            return "Data Summary: " + summary;
        } catch (Exception e) {
            return "Error generating summary: " + e.getMessage();
        }
    }

    private String generateFileReport(Map<String, Object> params) {
        try {
            var filename = (String) params.get("filename");
            var path = Path.of(filename);
            
            if (Files.exists(path)) {
                var size = Files.size(path);
                var modified = Files.getLastModifiedTime(path);
                
                return String.format("""
                    File Report:
                    Name: %s
                    Size: %d bytes
                    Modified: %s
                    """, filename, size, modified);
            } else {
                return "File not found: " + filename;
            }
        } catch (IOException e) {
            return "File error: " + e.getMessage();
        }
    }

    private String performValidationCheck(Map<String, Object> params) {
        var input = (String) params.get("input");
        var rules = (List<String>) params.get("rules");
        
        var results = rules.stream()
            .map(rule -> Map.of(
                "rule", rule,
                "valid", validateRule(input, rule),
                "checkedAt", LocalDateTime.now()
            ))
            .toList();
        
        return "Validation Results: " + results;
    }

    private boolean validateRule(String input, String rule) {
        return switch (rule) {
            case "not-empty" -> input != null && !input.trim().isEmpty();
            case "min-length-5" -> input != null && input.length() >= 5;
            case "contains-digit" -> input != null && input.matches(".*\\d.*");
            case "valid-email" -> input != null && input.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
            default -> false;
        };
    }

    // Quick utility methods that feel like scripting
    public String quickFormat(String template, Object... args) {
        return String.format(template, args);
    }

    public List<String> quickFilter(List<String> items, String filter) {
        return items.stream()
            .filter(item -> item.contains(filter))
            .toList();
    }

    public Map<String, Object> quickMap(Object... keyValuePairs) {
        var map = new java.util.HashMap<String, Object>();
        for (int i = 0; i < keyValuePairs.length; i += 2) {
            if (i + 1 < keyValuePairs.length) {
                map.put(keyValuePairs[i].toString(), keyValuePairs[i + 1]);
            }
        }
        return map;
    }
}