package ru.qa.day12;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.qa.base.BaseApiTest;
import ru.qa.dto.CreatePostRequest;
import ru.qa.dto.PostResponse;
import ru.qa.dto.UpdatePostRequest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

public class PostPutDeleteTest extends BaseApiTest {



    @Test
    @DisplayName("POST /posts should create a new post and response 201")
    void createPost() {
        CreatePostRequest request = CreatePostRequest.builder()
                                                     .title("First post")
                                                     .body("First post body")
                                                     .userId(1)
                                                     .build();
        PostResponse postResponse = given()
                .spec(requestSpec)
                .body(request)
                .when()
                .post("/posts")
                .then()
                .spec(responseSpec)
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
                .spec(requestSpec)
                .body(request)
                .log().body()
                .when()
                .put("/posts/1")
                .then()
                .spec(responseSpec)
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
                .spec(requestSpec)
                .when()
                .delete("/posts/1")
                .then()
                .spec(responseSpec)
                .statusCode(200)
                .body("isEmpty()", is(true));
    }

    @Test
    @DisplayName("DELETE /posts/9999 should handle non-existent resource")
    void shouldDeleteNonExistentPost(){
        int statusCode  = given()
                .spec(requestSpec)
                .when()
                .delete("/posts/9999")
                .then()
                .spec(responseSpec)
                .extract().statusCode();
        //jsonplaceholder as fakeAPI for learning returns 200 instead of 404
        assertTrue(statusCode == 404 || statusCode == 200);
    }

    @Test
    @DisplayName("POST /posts with empty title should fail validation")
    void shouldFailPostWithEmptyTitle(){
        CreatePostRequest request = CreatePostRequest.builder()
                .title("")
                .body("Content")
                .userId(1)
                .build();

        int statusCode = given()
                .spec(requestSpec)
                .body(request)
                .log().body() //log request body
                .when()
                .post("/posts")
                .then()
                .spec(responseSpec)
                .extract ().statusCode();
// response 201 as host is fake API. real project returns 4xx code
// In real project, change to: assertNotEquals(201, statusCode)
        assertEquals(201, statusCode);
    }

    @ParameterizedTest
    @CsvSource({
            "First post, First body, 1",
            "Second post, Second body, 2",
            "Test title, Test content, 3"
    })
    @DisplayName("POST /posts should create posts with different data")
    void shouldCreatePostWithDifferentData(String title, String body, int userId){
        CreatePostRequest request = CreatePostRequest.builder()
                .title(title)
                .body(body)
                .userId(userId)
                .build();

        PostResponse response = given()
                .spec(requestSpec)
                .body(request)
                .when()
                .post("/posts")
                .then()
                .spec(responseSpec)
                .extract().as(PostResponse.class);

        assertEquals(title, response.getTitle());
        assertEquals(body, response.getBody());
        assertEquals(userId, response.getUserId());
        assertEquals(title, response.getTitle());
    }
}
