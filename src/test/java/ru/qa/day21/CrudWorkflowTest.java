package ru.qa.day21;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import ru.qa.assertions.PostDtoAssertions;
import ru.qa.dto.CreatePostRequest;
import ru.qa.dto.PostDto;
import ru.qa.dto.UserDto;

import java.util.List;

import static io.restassured.RestAssured.given;
import static ru.qa.specs.ApiSpecs.*;

public class CrudWorkflowTest {

    @Nested
    @DisplayName("Positive case")
    class PositiveCases {

        @Test
        @DisplayName("Full CRUD workflow: create → read → update → delete")
        void shouldPerformFullCrudWorkflow() {

            // Step 1. CREATE POST /posts с телом
            // Save id in response
            CreatePostRequest request = CreatePostRequest.builder()
                                                         .title("Test Title")
                                                         .body("Test body")
                                                         .userId(1)
                                                         .build();

            CreatePostRequest updateRequest = CreatePostRequest.builder()
                                                               .title("Update Title")
                                                               .body("Update test in body")
                                                               .build();

            int createdId = given()
                    .spec(loggingRequestSpec())
                    .body(request)
                    .post("/posts")
                    .then()
                    .spec(createdResponseSpec())
                    .extract().jsonPath().getInt("id");
            System.out.println("createdId: " + createdId); // created ID - 101, here used FAKE API

            // Step 2: READ. GET /posts/{id}
            // Use existed ID 1-100 as FAKE API
            given()
                    .spec(loggingRequestSpec())
                    .when()
                    .get("/posts/1") //here check
                    .then()
                    .statusCode(200);

            // Step 3: UPDATE. PUT
            // Update existed ID 1-100 as FAKE API
            given()
                    .spec(baseRequestSpec())
                    .body(updateRequest)
                    .when()
                    .put("/posts/1")
                    .then()
                    .statusCode(200);

            // Step 4: DELETE
            // Delete id 1-100 as fake API
            given()
                    .spec(baseRequestSpec())
                    .when()
                    .delete("/posts/1")
                    .then()
                    .statusCode(200);
        }

        @Test
        @DisplayName("User workflow: get user → get user posts → verify posts belong to user")
        void shouldGetUserAndHisPosts() {
            int requestId = 1;
            // Step 1: GET /users/1
            // Save userId and name from response
            UserDto user = given()
                    .spec(baseRequestSpec())
                    .when()
                    .get("posts/users" + requestId)
                    .then()
                    .extract().body().as(UserDto.class);
            Integer responseUserId = user.getId();
            String responseName = user.getName();

            // Step 2: GET /users/{userId}/posts
            // Get list of posts
            List<PostDto> posts = given()
                    .spec(baseRequestSpec())
                    .pathParams("id", responseUserId)
                    .when()
                    .get("users/{id}/posts")
                    .then()
                    .extract().body().jsonPath().getList(".", PostDto.class);

            // Step 3: Check all posts belongs to userId
            posts.forEach(post -> {
                PostDtoAssertions.assertUserPost(post, responseUserId);
            });

        }
    }

    @Nested
    @DisplayName("Negative case")
    class NegativeCases {

        @Test
        @DisplayName("Should return 404 when posts/{id} - is not exist")
        void shouldReturn404ForNonExistentPost() {
            int requestId = 9999;
            given()
            .spec(loggingRequestSpec())
            .pathParams("id", requestId)
            .when()
            .get("posts/{id}" )
            .then()
            .spec(clientErrorResponseSpec());
        }

        @Test
        @DisplayName("Should return 404 when user/{id} - is not exist")
        void shouldReturn404ForNonExistentUser() {
            int requestId = 9999;
            given()
            .spec(loggingRequestSpec())
            .pathParams("id", requestId)
            .when()
            .get("users/{id}")
            .then()
            .spec(clientErrorResponseSpec());
        }

    }
}

