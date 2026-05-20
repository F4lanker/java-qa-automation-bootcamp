package ru.qa.base;

import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import ru.qa.config.constansts.AuthConfig;
import ru.qa.day23.BearerTokenTest;
import ru.qa.specs.ApiSpecs;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static ru.qa.config.constansts.ApiConfig.REQRES_URL;
import static ru.qa.specs.ApiSpecs.*;

public abstract class BaseAuthApiTest {
    protected static String accessToken;
    protected static RequestSpecification authSpec;

    @Config.Sources("classpath:apikey.properties")
    public interface ApiConfig extends Config {
        @Key("reqres.api.key")
        String apiKey();
    }

    static BearerTokenTest.ApiConfig cfg = ConfigFactory.create(BearerTokenTest.ApiConfig.class);

    @BeforeAll
    static void authenticate() {
        accessToken = given()
                .spec(httpRequestSpec())
                .header("X-API-Key", cfg.apiKey())
                .body(Map.of("email", AuthConfig.REQRES_EMAIL,
                             "password", AuthConfig.REQRES_PASSWORD))
                .when()
                .get(AuthConfig.REQRES_LOGIN_PATH)
                .then()
                .extract().jsonPath().getString("accessToken");

        authSpec = ApiSpecs.authRequestSpec(REQRES_URL ,accessToken).header("X-API-Key", cfg.apiKey());
    }
}
