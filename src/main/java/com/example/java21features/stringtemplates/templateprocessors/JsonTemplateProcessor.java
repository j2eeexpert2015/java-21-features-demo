package com.example.java21features.stringtemplates.templateprocessors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonTemplateProcessor {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static JsonTemplate of(String template, Object... values) {
        return new JsonTemplate(template, values);
    }

    public static final class JsonTemplate {
        private final String jsonString;
        private final ObjectNode jsonNode;

        private JsonTemplate(String template, Object[] values) {
            this.jsonString = buildJsonString(template, values);
            this.jsonNode = parseJson(jsonString);
        }

        public String getJsonString() {
            return jsonString;
        }

        public ObjectNode getJsonNode() {
            return jsonNode;
        }

        public boolean isValid() {
            return jsonNode != null;
        }

        private String buildJsonString(String template, Object[] values) {
            // Simple template processing for JSON
            String result = template;
            for (int i = 0; i < values.length; i++) {
                String placeholder = "\\{" + i + "\\}";
                String value = escapeJsonValue(values[i]);
                result = result.replaceAll(placeholder, value);
            }
            return result;
        }

        private String escapeJsonValue(Object value) {
            if (value == null) return "null";
            if (value instanceof String) {
                return "\"" + ((String) value).replace("\"", "\\\"") + "\"";
            }
            if (value instanceof Number || value instanceof Boolean) {
                return value.toString();
            }
            return "\"" + value.toString().replace("\"", "\\\"") + "\"";
        }

        private ObjectNode parseJson(String jsonString) {
            try {
                return (ObjectNode) objectMapper.readTree(jsonString);
            } catch (Exception e) {
                throw new IllegalArgumentException("Invalid JSON template: " + jsonString, e);
            }
        }

        @Override
        public String toString() {
            return jsonString;
        }
    }
}