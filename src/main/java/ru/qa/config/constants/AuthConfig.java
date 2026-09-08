package ru.qa.config.constants;

public final class AuthConfig {
    public static final String REQRES_EMAIL = "eve.holt@reqres.in";
    public static final String REQRES_PASSWORD = "cityslicka";
    public static final String REQRES_LOGIN_PATH = "/api/login";
    public static final String RESFTFUL_BOOKER_AUTH = "/auth";


    private AuthConfig() {
        throw new AssertionError("Utility class");
    }
}
