package ru.qa.day24;

import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.qa.config.constansts.ApiKeyConfig;
import ru.qa.dto.LoginDto;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static ru.qa.specs.ApiSpecs.*;

public class AuthSessionTest {
    private static final ApiKeyConfig API_KEY_CONFIG = ConfigFactory.create(ApiKeyConfig.class);
    @Test
    @DisplayName("Token extracted from 1st request authorized in the 2nd request")
    void shouldAuthorisedViaTokenExtraction() {

        String email = "eve.holt@reqres.in";
        String password = "cityslicka";

        String token = given()
                .spec(httpRequestSpec())
                .header("X-API-Key", API_KEY_CONFIG.apiKey()) //can't authorize without the header. Here can be used @BeforeAll and extends BaseAuthApiTest. But the task was NOT use @Before all
                .body(( new LoginDto(email, password)))
                .when()
                .post("api/login")
                .then()
                .extract().jsonPath().getString("token");

        given()
                .spec(httpRequestSpec())
                .header("X-API-Key", API_KEY_CONFIG.apiKey())
                .header("Authorization", "Bearer " + token)  //authorization works without this line and token, only X-API-Key is required
                .when()
                .get("api/users/2")
                .then()
                .statusCode(200)
                .body("data.id" , equalTo(2));

    }

}
