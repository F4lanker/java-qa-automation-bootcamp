package ru.qa.day20;

import org.junit.jupiter.api.Test;
import ru.qa.base.BaseApiTest;
import ru.qa.specs.ApiSpecs;

import static io.restassured.RestAssured.given;

;

public class RefactoredApiTest extends BaseApiTest {

    @Test
    void shouldUseBaseRequestSpec(){
        given()
                .spec(ApiSpecs.baseRequestSpec()) // здесь хочется сделать так же как с  .spec(requestSpec) чтобы можно было использовать без указания класса. Как это сделать?
                .get(getBasePath() + "/1")
                .then()
                .spec(ApiSpecs.successResponseSpec());
    }

    @Test
    void shouldUseLoggingRequestSpec(){
        given()
                .spec(ApiSpecs.loggingRequestSpec())
                .get(getBasePath() + "/1")
            .then()
                .spec(ApiSpecs.successResponseSpec());
    }
@Test
void shouldUseCreatedResponseSpec(){
        given()
                .spec(ApiSpecs.baseRequestSpec())
                .post(getBasePath())
                .then()
                .spec(ApiSpecs.createdResponseSpec());
}

@Test
void shouldCombineMultipleSpecs(){
        given()
                .spec(ApiSpecs.baseRequestSpec())
                .queryParam("userId", 1)
                .get(getBasePath())
                .then()
                .spec(ApiSpecs.successResponseSpec());
}

    @Override
    protected String getBasePath() {
        return "/posts";
    }
}
