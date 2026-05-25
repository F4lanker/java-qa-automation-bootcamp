package ru.qa.base;

import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import ru.qa.config.constansts.ApiKeyConfig;
import ru.qa.config.constansts.AuthConfig;
import ru.qa.specs.ApiSpecs;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static ru.qa.config.constansts.ApiConfig.REQRES_URL;
import static ru.qa.specs.ApiSpecs.*;

public abstract class BaseAuthApiTest {
    protected static String accessToken;
    protected static RequestSpecification authSpec;


   protected static final ApiKeyConfig API_KEY_CONFIG = ConfigFactory.create(ApiKeyConfig.class);

    @BeforeAll
    static void authenticate() {
        accessToken = given()
                .spec(reqresSpec())
                .header("X-API-Key", API_KEY_CONFIG.apiKey())
                .body(Map.of("email", AuthConfig.REQRES_EMAIL,
                             "password", AuthConfig.REQRES_PASSWORD))
                .when()
                .post(AuthConfig.REQRES_LOGIN_PATH)
                .then()
                .extract().jsonPath().getString("token");

        authSpec = ApiSpecs.authRequestSpec(REQRES_URL ,accessToken).header("X-API-Key", API_KEY_CONFIG.apiKey());
    }
}
