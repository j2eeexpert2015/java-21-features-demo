package com.example.java21features.sequencedcollections;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SequencedCollectionsDemoTest {

    @Test
    public void testDemoRunsWithoutErrors() {
        // This is a simple test to ensure the demo runs without exceptions
        SequencedCollectionsDemo demo = new SequencedCollectionsDemo();
        
        // Should run without throwing exceptions
        demo.demoLinkedHashSetBoundedCache();
        demo.demoPaginationWithSequencedMap();
        demo.processRecentActivityFeed();
        
        assertTrue(true, "All demo methods executed successfully");
    }
}