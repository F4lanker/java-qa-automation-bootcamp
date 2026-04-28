package ru.qa.day20;

import org.junit.jupiter.api.Test;
import ru.qa.base.BaseApiTest;
import ru.qa.dto.CreatePostRequest;
import ru.qa.specs.ApiSpecs;

import static io.restassured.RestAssured.given;
import static ru.qa.specs.ApiSpecs.baseRequestSpec;
import static ru.qa.specs.ApiSpecs.loggingRequestSpec;

;

public class RefactoredApiTest extends BaseApiTest {

    @Test
    void shouldUseBaseRequestSpec(){
        given()
                .spec(baseRequestSpec())
                .when()
                .get(getBasePath() + "/1")
                .then()
                .spec(ApiSpecs.successResponseSpec());
    }

    @Test
    void shouldUseLoggingRequestSpec(){
        given()
                .spec(loggingRequestSpec())
                .get(getBasePath() + "/1")
            .then()
                .spec(ApiSpecs.successResponseSpec());
    }
@Test
void shouldUseCreatedResponseSpec(){
    CreatePostRequest request = CreatePostRequest.builder()
            .title("Test Post")
            .body("Test Body")
            .userId(1)
            .build();

        given()
                .spec(baseRequestSpec())
                .body(request)
                .when()
                .post(getBasePath())
                .then()
                .spec(ApiSpecs.createdResponseSpec());
}

@Test
void shouldCombineMultipleSpecs(){
        given()
                .spec(baseRequestSpec())
                .queryParam("userId", 1)
                .when()
                .get(getBasePath())
                .then()
                .spec(ApiSpecs.successResponseSpec());
}

    @Override
    protected String getBasePath() {
        return "/posts";
    }
}
