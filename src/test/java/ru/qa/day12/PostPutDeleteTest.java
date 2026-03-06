package ru.qa.day12;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.qa.dto.CreatePostRequest;
import ru.qa.dto.PostResponse;
import ru.qa.dto.UpdatePostRequest;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import static ru.qa.day10.ApiConfig.BASE_URL;

public class PostPutDeleteTest {

    private RequestSpecification spec;

    @BeforeEach
    public void setUp() {
        spec = new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setContentType(ContentType.JSON)
                .build();
    }

    @Test
    @DisplayName("POST /posts should create a new post and response 201")
    void createPost() {
        CreatePostRequest request = CreatePostRequest.builder()
                                                     .title("First post")
                                                     .body("First post body")
                                                     .userId(1)
                                                     .build();
        PostResponse postResponse = given()
                .spec(spec)
                .body(request)
                .when()
                .post("/posts")
                .then()
                .log().ifError()
                .statusCode(201)
                .extract().as(PostResponse.class);
        assertNotNull(postResponse.getBody());
        assertNotNull(postResponse.getTitle());
        assertTrue(postResponse.getUserId() > 0);
        assertEquals("First post", postResponse.getTitle());
        assertTrue(postResponse.getId() > 0);
    }

    @Test
    @DisplayName("PUT /posts/1 should update post and return 200")
    void shouldUpdateExistingPost(){
        UpdatePostRequest request = UpdatePostRequest.builder()
                .id(1)
                .title("Up-Title")
                .body("Update context")
                .userId(1)
                .build();
        PostResponse response = given()
                .spec(spec)
                .body(request)
                .when()
                .put("/posts/1")
                .then()
                .log().ifError()
                .statusCode(200)
                .extract().as(PostResponse.class);

        assertEquals("Up-Title", response.getTitle());
        assertEquals("Update context", response.getBody());
        assertEquals(1, response.getUserId());
    }

    @Test
    @DisplayName("DELETE /posts/1 should return code 200")
    void shouldDeleteExistingPost(){
        given()
                .spec(spec)
                .when()
                .delete("/posts/1")
                .then()
                .log().ifError()
                .statusCode(200)
                .extract().as(PostResponse.class);
    }

    @Test
    @DisplayName("DELETE /posts/9999 should handle non-existent resource")
    void shouldDeleteNonExistentPost(){
        int statusCode  = given()
                .spec(spec)
                .when()
                .delete("/posts/9999")
                .then()
                .log().ifError()
                .extract().statusCode();
        //jsonplaceholder as fakeAPI for learning returns 200 instead of 404
        assertTrue(statusCode == 404 || statusCode == 200);
    }
}
