package ru.qa.day21;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.qa.dto.CreatePostRequest;

import static io.restassured.RestAssured.given;
import static ru.qa.specs.ApiSpecs.*;

public class CrudWorkflowTest {



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

}

