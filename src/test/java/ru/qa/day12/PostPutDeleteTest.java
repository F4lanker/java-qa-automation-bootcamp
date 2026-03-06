package ru.qa.day12;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.qa.dto.CreatePostRequest;
import ru.qa.dto.PostResponse;

import static io.restassured.RestAssured.given;
import static ru.qa.day10.ApiConfig.BASE_URL;

public class PostPutDeleteTest {

    private RequestSpecification spec;

    @BeforeEach
    public void setUp() {
        spec = new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
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
        RequestSpecification spec;
        PostResponse postResponse = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .extract().as(PostResponse.class);
    }
}
