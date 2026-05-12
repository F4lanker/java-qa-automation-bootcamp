package ru.qa.day23;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.qa.specs.ApiSpecs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class BasicAuthTest {
    // Endpoint: GET /basic-auth/{user}/{passwd}
    // Required Basic Auth: user="user", password="passwd"

    @Test
    @DisplayName("Should pass with valid credentials")
    void shouldPassWithValidCredentials(){
      given()
              .spec(ApiSpecs.loggingRequestSpec("https://httpbin.org/"))
              .auth().basic("user", "passwd")
              .when()
              .get("basic-auth/user/passwd")
              .then()
              .log().all()
              .statusCode(200)
              .body("authenticated", equalTo(true));
    }
}
