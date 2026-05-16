package ru.qa.config.constansts;

/**
 * Configuration constants for API tests.
 * This is a utility class - cannot be instantiated or inherited.
 */

public final class ApiConfig {
    public static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    public static final String HTTPBIN_URL = "https://httpbin.org";
    public static final String REQRES_URL = "https://reqres.in";

    private ApiConfig() { // ✅ private конструктор
        throw new AssertionError("Utility class");
    }

}
