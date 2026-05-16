package ru.qa.day23;

import io.restassured.http.ContentType;
import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.qa.dto.LoginDto;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static ru.qa.specs.ApiSpecs.*;

public class BearerTokenTest {


    @Config.Sources("classpath:apikey.properties")
    public interface ApiConfig extends Config {
        @Key("reqres.api.key")
        String apiKey();
    }

    ApiConfig cfg = ConfigFactory.create(ApiConfig.class);


    @Test
    @DisplayName("Should return not NULL with status code 200")
    void shouldGetTokenOnValidLogin() {

        String email = "eve.holt@reqres.in";
        String password = "cityslicka";

        given()
                .spec(httpRequestSpec())
                .header("X-API-Key", cfg.apiKey())
                .contentType(ContentType.JSON)
                .body(new LoginDto(email, password))
                .log().all()
                .when()
                .post("/api/login")
                .then()
                .statusCode(200)
                .body("token", notNullValue());
    }

    @Test
    @DisplayName("Should fail with invalid credentials")
    void shouldFailWithInvalidCredentials() {

        String email = "mail";
        String password = "pass";

        given()
                .spec(httpRequestSpec())
                .header("X-API-Key", cfg.apiKey())
                .contentType(ContentType.JSON)
                .body(new LoginDto(email, password))
                .log().all()
                .when()
                .post("/api/login")
                .then()
                .statusCode(400);

    }
}
