package ru.qa.day22;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static ru.qa.specs.ApiSpecs.*;

public class NegativePostsTest {
    @DisplayName("Negative tests for /posts")
    @Nested
    class NegativePostsTests {

        @ParameterizedTest
        @ValueSource(ints = {0, -1, 99999, Integer.MAX_VALUE})
        @DisplayName("GET: non existen /post should response with 404 code")
        void shouldReturn404ForNonExistentPost(int id) {
            given()
                    .spec(baseRequestSpec())
                    .when()
                    .get("/posts/" + id)
                    .then()
                    .spec(notFoundResponseSpec());
        }

        @Test
        @DisplayName("POST: Should return 400 if request body is empty (fake API returns 201)")
        void shouldReturn400IfRequestBodyIsEmpty() {
            given()
                    .spec(baseRequestSpec())
                    .when()
                    .post("/posts")
                    .then()
                    .spec(createdResponseSpec()); //fake API responses 201, but real API should change to clientErrorResponseSpec()
        }
    }

    @Nested
    class NegativeTestForUsers {
//        shouldReturn404ForNonExistentUser — GET /users/99999

        @ParameterizedTest
        @ValueSource(ints = {0, -1, 99999, Integer.MAX_VALUE})
        @DisplayName("GET: Should response  404 if user is non existen")
        void shouldReturn404ForNonExistentUser(int id) {
            given()
                    .spec(baseRequestSpec())
                    .when()
                    .get("/users/" + id)
                    .then()
                    .spec(notFoundResponseSpec());

        }

        @ParameterizedTest
        @ValueSource(ints = {0, -1, 99999, Integer.MAX_VALUE})
        @DisplayName("GET: Should return 400 when GET /users/{nonExistenId}/posts (fake API returns 200)")
        void shouldReturnEmptyListForNonExistentUserPosts(int id) {
            // NOTE: jsonplaceholder returns 200 with empty array [] for non-existent users.
            // Real API should return 404.
            given()
                    .spec(loggingRequestSpec())
                    .when()
                    .get("/users/" + id + "/posts")
                    .then()
                    .statusCode(200)
                    .body("$", hasSize(0));;
        }
    }
}
