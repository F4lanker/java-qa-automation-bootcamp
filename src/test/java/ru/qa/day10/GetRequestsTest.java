package ru.qa.day10;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static ru.qa.day10.ApiConfig.BASE_URL;

public class GetRequestsTest {

    private RequestSpecification spec;
    @BeforeEach
    void setUp() {
        spec = new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .build();
    }

    @Test
    @DisplayName("GET /todos/1 - should return status 200")
    void successStatusTest() {
    given()
            .spec(spec)
            .log().ifValidationFails()
    .when()
            .get("/todos/1")
    .then()
            .log().ifError()
            .statusCode(200);

    }

    @Test
    @DisplayName("/todos/1 should have title")
    void bodyHasTitleTest() {
    given()
            .spec(spec)
            .log().ifValidationFails()
    .when()
            .get("/todos/1")
    .then()
            .log().ifError()
            .body("title", not(emptyString()));
    }

    @Test
    @DisplayName("/users should have 10 items")
    void userSizeTest() {
        int size = 10; // jsonplaceholder returns 10 users
    given()
            .spec(spec)
            .log().ifValidationFails()
    .when()
            .get("/users")
     .then()
            .log().ifError()
            .statusCode(200)
            .body("", hasSize(size));
    }

    @Test
    @DisplayName("/users the first user should has name field")
    void firstNameTest() {
        given()
                .spec(spec)
                .log().ifValidationFails()
        .when()
                .get("/users")
        .then()
                .log().ifError()
                .body("[0]", hasKey("name")); //check that the user has Name field, null value is acceptable as well
    }
    @Test
    @DisplayName("/users the first user name - Leanne Graham ")
    void userNameTest() {
    String expectedUserName = "Leanne Graham";
    given()
            .spec(spec)
            .log().ifValidationFails()
    .when()
            .get("/users")
    .then()
            .log().ifError()
            .body("[0].name", equalTo(expectedUserName));
    }
}
