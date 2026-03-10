package ru.qa.day11;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.common.mapper.TypeRef;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.qa.base.BaseApiTest;
import ru.qa.day9.util.JsonUtils;
import ru.qa.dto.TodoDto;
import ru.qa.dto.UserDto;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class TodoDtoDeserializationTest extends BaseApiTest {


    @Test
    @DisplayName("GET /todos/1 - Should return POJO as response")
    void deserializeTodo() {
        TodoDto response = given()
                .spec(requestSpec)
                .log().ifValidationFails()
                .when()
                .get("/todos/1")
                .then()
                .spec(responseSpec)
                .statusCode(200)
                .extract().as(TodoDto.class);
        assertAll(
                () -> assertEquals(1, response.getId()),
                () -> assertFalse(response.isCompleted())
                            );

    }

    @Test
    @DisplayName("GET /todos - Should return the list of Todos")
    void deserializeTodosList() {
        List<TodoDto> todoList = given()
                .spec(requestSpec)
                .log().ifValidationFails()
                .when()
                .get("/todos")
                .then()
                .spec(responseSpec)
                .statusCode(200)
                .extract().as(new TypeRef<List<TodoDto>>() {
                });
        assertAll(
                () -> assertFalse(todoList.isEmpty()),
                () -> assertNotNull(todoList.get(0)),
                () -> assertEquals(1, todoList.get(0).getId())
                            );
    }
    @Test
    @DisplayName("Deserialized UserDto should contain 'name' field in JSON")
    void jsonConsistFieldName() throws JsonProcessingException {
        UserDto userDto = given()
                .spec(requestSpec)
                .log().ifValidationFails()
                .when()
                .get("/users/1")
                .then()
                .spec(responseSpec)
                .statusCode(200)
                .extract().as(UserDto.class);

        String json = JsonUtils.toPrettyJson(userDto);

        assertTrue(json.contains("\"name\""));
        assertNotNull(userDto.getName());
    }

    @Test
    @DisplayName("GET /users/1 should return user with non-empty city")
    void userCityNoEmpty() {
        UserDto userDto = given()
                .spec(requestSpec)
                .log().ifValidationFails()
                .when()
                .get("/users/1")
                .then()
                .spec(responseSpec)
                .statusCode(200)
                .extract().as(UserDto.class);
        assertNotNull(userDto.getAddress().getCity());
        assertEquals("Gwenborough", userDto.getAddress().getCity());
        assertNotNull(userDto.getAddress().getGeo().getLat());
        assertEquals("-37.3159", userDto.getAddress().getGeo().getLat());
        assertNotNull(userDto.getAddress().getGeo().getLng());

    }
}
