package ru.qa.day24;

import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.qa.config.constansts.ApiKeyConfig;
import ru.qa.dto.LoginDto;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static ru.qa.specs.ApiSpecs.*;

public class AuthSession {

    @Test
    @DisplayName("Token extracted from 1-st reauest authorised in the 2-d request")
    void shouldAuthorisedViaTokenExtraction() {

        final ApiKeyConfig API_KEY_CONFIG = ConfigFactory.create(ApiKeyConfig.class);
        String email = "eve.holt@reqres.in";
        String password = "cityslicka";

        String token = given()
                .spec(httpRequestSpec())
                .header("X-API-Key", API_KEY_CONFIG.apiKey()) //без заголовка нельзя авторизоваться, чтобы избежать @BeforeAll продублировал API_KEY_CONFIG здесь, если есть вариант лучше объясни
                .body(( new LoginDto(email, password)))
                .when()
                .post("api/login")
                .then()
                .extract().jsonPath().getString("token");

        given()
                .spec(httpRequestSpec())
                .header("X-API-Key", API_KEY_CONFIG.apiKey())
                //.header("Authorization", "Bearer " + token)  //авторизация срабатыввает без токена, с одиним только
                .when()
                .get("api/users/2")
                .then()
                .statusCode(200)
                .body("data.id" , equalTo(2));

    }

}
