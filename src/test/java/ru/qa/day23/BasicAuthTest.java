package ru.qa.day23;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static ru.qa.specs.ApiSpecs.*;

public class BasicAuthTest {
    // Endpoint: GET /basic-auth/{user}/{passwd}
    // Required Basic Auth: user="user", password="passwd"

    @Test
    @DisplayName("Should pass with valid credentials")
    void shouldPassWithValidCredentials(){
      given()
              .spec(loggingRequestSpec("https://httpbin.org/"))
              .auth().basic("user", "passwd")
              .when()
              .get("basic-auth/user/passwd")
              .then()
              .log().all()
              .statusCode(200)
              .body("authenticated", equalTo(true));
    }

    @ParameterizedTest
    @CsvSource({"user, password", "usr, passwd", "1, 1", "user, user", " '','' "}) // here I test empty values as well
    @DisplayName("Should return 401 if credentials is not correct or empty")
    void shouldFailWithInvalidOrEmptyCredentials(String login, String password){
        given()
                .spec(loggingRequestSpec("https://httpbin.org/"))
                .auth().basic(login, password)
                .when()
                .get("basic-auth/user/passwd")
                .then()
                .log().all()
                .statusCode(401);
    }

}
