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
    @DisplayName("Test of title is NOT empty")
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
    @DisplayName("Test user list has {{size}} quantity of items")
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
    @DisplayName("Check the first user has the name parameter")
    void firstNameTest() {
        given()
                .spec(spec)
                .log().ifValidationFails()
        .when()
                .get("/users")
        .then()
                .log().ifError()
                .body("[0].name", notNullValue());
    }
    @Test
    @DisplayName("Check the first user has correct name")
    void userNameTest() {
    String expectedUserName = "Leanne Graham";
    given()
            .spec(spec)
            .log().ifValidationFails()
    .when()
            .get("/users")
    .then()
            .log().ifError()
            .body("name[0]", equalTo(expectedUserName));
    }
}
