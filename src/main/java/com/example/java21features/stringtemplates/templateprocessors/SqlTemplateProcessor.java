package com.example.java21features.stringtemplates.templateprocessors;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.regex.Pattern;

public class SqlTemplateProcessor {

    // Simple SQL injection validation pattern
    private static final Pattern SQL_INJECTION_PATTERN = 
        Pattern.compile("([';]+|(--)+|(\\*)+|(\\b)(DROP|DELETE|INSERT|UPDATE|SELECT|UNION|EXEC)(\\b))", 
                       Pattern.CASE_INSENSITIVE);

    public static SqlTemplate of(String template, Object... values) {
        return new SqlTemplate(template, List.of(values));
    }

    public static final class SqlTemplate {
        private final String query;
        private final List<Object> parameters;

        private SqlTemplate(String query, List<Object> parameters) {
            this.query = validateSql(query);
            this.parameters = parameters;
        }

        public String getQuery() {
            return query;
        }

        public List<Object> getParameters() {
            return parameters;
        }

        public PreparedStatement createPreparedStatement(java.sql.Connection connection) 
                throws java.sql.SQLException {
            PreparedStatement stmt = connection.prepareStatement(query);
            for (int i = 0; i < parameters.size(); i++) {
                stmt.setObject(i + 1, parameters.get(i));
            }
            return stmt;
        }

        @Override
        public String toString() {
            return "SQL: " + query + " | Params: " + parameters;
        }
    }

    private static String validateSql(String sql) {
        if (SQL_INJECTION_PATTERN.matcher(sql).find()) {
            throw new IllegalArgumentException("Potential SQL injection detected in query: " + sql);
        }
        return sql.replace("\\{", "{").replace("\\}", "}"); // Allow escaped braces
    }
}