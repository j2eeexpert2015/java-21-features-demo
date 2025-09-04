package com.example.java21features.stringtemplates;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringTemplatesDemoTest {

    @Test
    public void testBasicInterpolation() {
        StringTemplatesDemo demo = new StringTemplatesDemo();
        assertDoesNotThrow(demo::demoBasicInterpolation);
    }

    @Test
    public void testSafeSqlQueries() {
        StringTemplatesDemo demo = new StringTemplatesDemo();
        assertDoesNotThrow(demo::demoSafeSqlQueries);
    }

    @Test
    public void testJsonGeneration() {
        StringTemplatesDemo demo = new StringTemplatesDemo();
        assertDoesNotThrow(demo::demoJsonGeneration);
    }
}