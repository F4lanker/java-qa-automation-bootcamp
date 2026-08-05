package ru.qa.config.constansts;

public final class AuthConfig {
    public static final String REQRES_EMAIL = "eve.holt@reqres.in";
    public static final String REQRES_PASSWORD = "cityslicka";
    public static final String REQRES_LOGIN_PATH = "/api/login";

    private AuthConfig() {
        throw new AssertionError("Utility class");
    }
}
