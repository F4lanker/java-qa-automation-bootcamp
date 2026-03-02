package ru.qa.day11.dto;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
    @DisplayName("Should return POJO as response")
    void deserializeTodo() {
        Todo response = given()
                .spec(spec)
                .log().ifValidationFails()
                .when()
                .get("/todos/1")
                .then()
                .log().ifValidationFails()
                .extract().body().as(Todo.class);
        Assertions.assertAll(
                ()-> assertEquals(1, response.getId()),
                ()-> assertNotNull(response.getUserId()),
                ()-> assertFalse(response.isCompleted())
        );

    }
}
