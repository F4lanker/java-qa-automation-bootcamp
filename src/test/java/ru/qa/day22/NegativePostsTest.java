package ru.qa.day22;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.qa.specs.ApiSpecs;

import static io.restassured.RestAssured.given;

public class NegativePostsTest {
    @DisplayName("Negative tests for /posts")
    @Nested
    class NegativePostsTests {

        @ParameterizedTest
        @ValueSource(ints = {0, -1, 99999, Integer.MAX_VALUE})
        @DisplayName("GET: non existen /post should response with 404 code")
        void shouldReturn404ForNonExistentPost(int id) {
            given()
                    .spec(ApiSpecs.baseRequestSpec())
                    .when()
                    .get("/posts/" + id)
                    .then()
                    .spec(ApiSpecs.notFoundResponseSpec());
        }

        @Test
        @DisplayName("POST: Should return 400 if request body is empty (fake API returns 201)")
        void shouldReturn400IfRequestBodyIsEmpty() {
            given()
                    .spec(ApiSpecs.baseRequestSpec())
                    .when()
                    .post("/posts")
                    .then()
                    .spec(ApiSpecs.createdResponseSpec()); //fake API responses 201, but real API should change to clientErrorResponseSpec()
        }
    }

    @DisplayName("Negative tests for /users")
    @Nested
    class NegativeTestForUsers {
//        shouldReturn404ForNonExistentUser — GET /users/99999

        @ParameterizedTest
        @ValueSource(ints = {0, -1, 99999, Integer.MAX_VALUE})
        @DisplayName("GET: Should response  404 if user is non existen")
        void shouldReturn404ForNonExistentUser(int id) {
            given()
                    .spec(ApiSpecs.baseRequestSpec())
                    .when()
                    .get("/users/" + id)
                    .then()
                    .spec(ApiSpecs.notFoundResponseSpec());

        }

        @ParameterizedTest
        @ValueSource(ints = {0, -1, 99999, Integer.MAX_VALUE})
        @DisplayName("GET: Should return 400 when GET /users/{nonExistenId}/posts (fake API returns 200)")
        void shouldReturn404ForNonExistentUserPosts (int id) {
            given()
                    .spec(ApiSpecs.loggingRequestSpec())
                    .when()
                    .get("/users/" + id + "/posts")
                    .then()
                    .spec(ApiSpecs.successResponseSpec());
        }
    }
}
