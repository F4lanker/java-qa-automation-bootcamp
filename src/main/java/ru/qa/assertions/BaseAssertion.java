package ru.qa.assertions;

/**
 * Base class for all assertions.
 */
public abstract class BaseAssertion<T> {

    protected T actual;

    public BaseAssertion(T actual) {
        this.actual = actual;
    }

    /**
     * Each assertion implements its own verification logic.
     */
    public abstract void verify();

    /**
     * Template method - logs before verification.
     */
    public void assertThat() {
        System.out.println("[Assertion] Verifying: " + getDescription());
        verify();
        System.out.println("[Assertion] ✓ Passed");
    }

    protected abstract String getDescription();
}