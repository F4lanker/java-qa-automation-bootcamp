package ru.qa.day23;

import org.junit.jupiter.api.Test;
import ru.qa.base.BaseAuthApiTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AuthorisedUserTest extends BaseAuthApiTest {

    @Test
    void shouldGetUserWithToken() {
        given()
                .spec(authSpec)
                .when()
                .get("/api/users/2 ")
                .then()
                .statusCode(200)
                .body("data.id", equalTo(2));
    }
}
