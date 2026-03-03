package ru.qa.day11.dto;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.common.mapper.TypeRef;
import io.restassured.specification.RequestSpecification;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.qa.dto.User;
import ru.qa.day9.util.JsonUtils;
import ru.qa.dto.Todo;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import static ru.qa.day10.ApiConfig.BASE_URL;

public class TodoDesserializationTest {
    private RequestSpecification spec;

    @BeforeEach
    public void setUp() {
        spec = new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .build();
    }

    @Test
    @DisplayName("GET /todos/1 - Should return POJO as response")
    void deserializeTodo() {
        Todo response = given()
                .spec(spec)
                .log().ifValidationFails()
                .when()
                .get("/todos/1")
                .then()
                .log().ifValidationFails()
                .extract().body().as(Todo.class);
        assertAll(
                () -> assertEquals(1, response.getId()),
                () -> assertNotNull(response.getUserId()),
                () -> assertFalse(response.isCompleted())
                            );

    }

    @Test
    @DisplayName("GET /todos - Should return the list Todos")
    void deserializeTodosList() {
        List<Todo> response = given()
                .spec(spec)
                .log().ifValidationFails()
                .when()
                .get("/todos")
                .then()
                .log().ifValidationFails()
                .extract().body().as(new TypeRef<List<Todo>>() {
                });
        assertAll(
                () -> assertFalse(response.isEmpty()),
                () -> assertNotNull(response.get(0)),
                () -> assertEquals(1, response.get(0).getId())
                            );
    }
    @Test
    @DisplayName("GET user/1 serialized back from POJO should consist field 'name'")
    @SneakyThrows
    void jsonConsistFieldName() {
        User response = given()
                .spec(spec)
                .log().ifValidationFails()
                .when()
                .get("/users/1")
                .then()
                .log().ifValidationFails()
                .extract().body().as(User.class);

        String json = JsonUtils.toPrettyJson(response);

        assertTrue(json.contains("\"name\""));
        assertNotNull(response.getName());
    }

    @Test
    @DisplayName("GET user/1 city is not empty")
    void userCityNoEmpty() {
        User response = given()
                .spec(spec)
                .log().ifValidationFails()
                .when()
                .get("/users/1")
                .then()
                .log().ifValidationFails()
                .extract().body().as(User.class);
        assertNotNull(response.getAddress().getCity());
        assertEquals("Gwenborough", response.getAddress().getCity());
    }
}
