package ru.qa.day10;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static ru.qa.day10.ApiConfig.BASE_URL;

public class GetRequestsTest {

    @Test
    @DisplayName("HTTP status code: 200")
    public void successStatusTest() {
        String path = "todos";
        int param = 1;
    given()
            .baseUri(BASE_URL)
            .log().all()
            .pathParam("param", param)
            .pathParam("path", path)
    .when()
            .get("/{path}/{param}")
    .then()
            .log().ifError()
            .statusCode(200);

    }

    @Test
    @DisplayName("Test of title is NOT empty")
    void bodyHasTitleTest() {
    String path = "todos";
    int param = 1;
    given()
            .baseUri(BASE_URL)
            .pathParam("param", param)
            .pathParam("path", path)
            .log().all()
    .when()
            .get("/{path}/{param}")
    .then()
            .log().ifError()
            .body("title", not(emptyString()));
    }

    @Test
    @DisplayName("Test user list has {{size}} quantity of items")
    void userSizeTest() {
    String path = "users";
    int size = 10;
    given()
            .baseUri(BASE_URL)
            .pathParam("path", path)
            .log().all()
    .when()
            .get("/{path}")
     .then()
            .log().ifError()
            .statusCode(200)
            .body("", hasSize(size));
    }

    @Test
    @DisplayName("Check the first user has the name")
    void firstNameTest() {
        String path = "users";
        int userId = 0;
        given()
                .baseUri(BASE_URL)
                .pathParam("path", path)
                .log().all()
        .when()
                .get("/{path}")
        .then()
                .log().ifError()
                .body("name[0].size()", greaterThanOrEqualTo(userId));
    }
    @Test
    @DisplayName("Check the first user has correct name")
    void userNameTest() {
    String path = "users";
    int userId = 1;
    String expectedUserName = "Leanne Graham";
    given()
            .baseUri(BASE_URL)
            .pathParam("path", path)
            .log().all()
    .when()
            .get("/{path}")
    .then()
            .log().ifError()
            .body("name[0]", equalTo(expectedUserName));
    }
}
