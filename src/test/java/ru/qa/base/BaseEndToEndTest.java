package ru.qa.base;

import org.junit.jupiter.api.Test;

/**
 * Base class for End-to-End tests.
 * Implements Template Method pattern - defines the algorithm,
 * subclasses implement the steps.
 */
public abstract class BaseEndToEndTest extends BaseTest {

    /**
     * Template method - defines the test flow.
     * Subclasses must implement the abstract steps.
     */
    @Test
    public void endToEndScenario() {
        System.out.println("\n=== Starting E2E Test ===");

        prepareTestData();
        executeActions();
        verifyResults();
        cleanup();

        System.out.println("=== E2E Test Completed ===\n");
    }

    /**
     * Step 1: Prepare test data.
     * Each test implements its own data preparation logic.
     */
    protected abstract void prepareTestData();

    /**
     * Step 2: Execute main actions.
     * Each test implements its own business logic.
     */
    protected abstract void executeActions();

    /**
     * Step 3: Verify results.
     * Each test implements its own assertions.
     */
    protected abstract void verifyResults();

    /**
     * Step 4: Cleanup (common for all tests).
     * Runs automatically after test completion.
     */
    protected void cleanup() {
        System.out.println("[Cleanup] Removing test data");
    }
}