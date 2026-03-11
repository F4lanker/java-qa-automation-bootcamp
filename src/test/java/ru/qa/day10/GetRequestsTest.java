package ru.qa.day10;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.qa.base.BaseApiTest;
import ru.qa.dto.TodoDto;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetRequestsTest extends BaseApiTest {


    @Test
    @DisplayName("GET /todos/1 - should return status 200")
    void successStatusTest() {
    given()
            .spec(requestSpec)
            .log().ifValidationFails()
    .when()
            .get("/todos/1")
    .then()
            .spec(responseSpec)
            .statusCode(200);

    }

    @Test
    @DisplayName("/todos/1 should have title")
    void bodyHasTitleTest() {
    given()
            .spec(requestSpec)
            .log().ifValidationFails()
    .when()
            .get("/todos/1")
    .then()
            .spec(responseSpec)
            .body("title", not(emptyString()));
    }

    @Test
    @DisplayName("/users should have 10 items")
    void userSizeTest() {
        int size = 10; // jsonplaceholder returns 10 users
    given()
            .spec(requestSpec)
            .log().ifValidationFails()
    .when()
            .get("/users")
     .then()
            .spec(responseSpec)
            .statusCode(200)
            .body("", hasSize(size));
    }

    @Test
    @DisplayName("/users the first user should has name field")
    void firstNameTest() {
        given()
                .spec(requestSpec)
                .when()
                .get("/users")
                .then()
                .spec(responseSpec)
                .body("[0]", hasKey("name"));// Checks field presence (null value is valid)
    }
    @Test
    @DisplayName("/users the first user name - Leanne Graham ")
    void userNameTest() {
    String expectedUserName = "Leanne Graham";
    given()
            .spec(requestSpec)
            .log().ifValidationFails()
    .when()
            .get("/users")
    .then()
            .spec(responseSpec)
            .body("[0].name", equalTo(expectedUserName));
    }

    @ParameterizedTest()
    @ValueSource(ints= {1,2,3,5,10})
    @DisplayName("GET/ todos{id} should return 200 for valid IDs")
    void shouldReturnTodoForValidIds(int todoId) {
        TodoDto todoDto = given()
                .spec(requestSpec)
                .pathParam("id", todoId)
                .when()
                .get("/todos/{id}")
                .then()
                .spec(responseSpec)
                .statusCode(200)
                .extract().body().as(TodoDto.class);

        assertEquals(todoId, todoDto.getId());
    }
}
