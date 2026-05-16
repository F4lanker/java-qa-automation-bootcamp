package ru.qa.base;

import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.Config;
import org.junit.jupiter.api.BeforeAll;

public class BaseAuthApiTest {
    protected static String accessToken;
    protected static RequestSpecification authSpec;

    @Config.Sources("classpath:apikey.properties")
    public interface ApiConfig extends Config {
        @Key("reqres.api.key")
        String apiKey();
    }

    @BeforeAll
    static void authenticate() {
        // 1. Получи токен через POST /api/login
        // 2. Сохрани в accessToken
        // 3. Создай authSpec с Bearer токеном
    }
}
