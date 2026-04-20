package ru.qa.day17;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.qa.client.FlexibleApiClient;
import ru.qa.dto.CreatePostRequest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class OverloadingTest {

    private FlexibleApiClient client;

    @BeforeEach
    void setUp() {
        client = new FlexibleApiClient("/posts");
    }

    @Test
    @DisplayName("Overload 1: GET без параметров")
    void testGetWithoutParams() {
        Response response = client.get("");
        assertEquals(200, response.statusCode());
    }

    @Test
    @DisplayName("Overload 2: GET с одним query параметром")
    void testGetWithOneParam() {
        Response response = client.get("", "userId", 1);
        assertEquals(200, response.statusCode());
    }

    @Test
    @DisplayName("Overload 3: GET с Map параметров")
    void testGetWithMapParams() {
        Map<String, Object> params = Map.of(
                "userId", 1,
                "_limit", 5
                                           );
        Response response = client.get("", params);
        assertEquals(200, response.statusCode());
    }

    @Test
    @DisplayName("Overload 4: GET с path параметром")
    void testGetById() {
        Response response = client.getById("", 1);
        assertEquals(200, response.statusCode());
    }

    @Test
    @DisplayName("Overload 5: POST с Object")
    void testPostWithObject() {
        CreatePostRequest request = CreatePostRequest.builder()
                                                     .title("Test")
                                                     .body("Content")
                                                     .userId(1)
                                                     .build();

        Response response = client.post("", request);
        assertEquals(201, response.statusCode());
    }

    @Test
    @DisplayName("Overload 6: POST с Map")
    void testPostWithMap() {
        Map<String, Object> body = Map.of(
                "title", "Test",
                "body", "Content",
                "userId", 1
                                         );

        Response response = client.post("", body);
        assertEquals(201, response.statusCode());
    }
}