package ru.qa.base;

public class ApiTestConfig extends BaseConfig {

    // ✅ public static final — публичная константа
    public static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    // ✅ public static final — публичная константа
    protected static final String API_VERSION = "v1";

    // ✅ private конструктор — утилитный класс
    private ApiTestConfig() {
        throw new AssertionError("Utility class");
    }

    @Override
    protected String getConfigType() {
        return "API";
    }

    /**
     * Returns full API URL with version.
     * Public метод — используется в тестах.
     */
    public static String getFullApiUrl() {
        return BASE_URL + "/" + API_VERSION;
    }

}
