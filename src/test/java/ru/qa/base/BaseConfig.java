package ru.qa.base;

/**
 * Base configuration class for all test configs.
 * Provides common timeout settings and validation.
 */

public abstract class BaseConfig {
    protected static final int DEFAULT_TIMEOUT = 5000;
    private static final boolean STRICT_MODE = true;

    /**
     * Validates configuration on startup.
     * Package-private — got access only from the package.
     */

    static void validateConfig() {
        if (STRICT_MODE) {
            System.out.println("Strict mode ENABLED");
        }
    }

    /**
     * Abstract method — each confir returns its own Type.
     */
    protected abstract String getConfigType();
}
