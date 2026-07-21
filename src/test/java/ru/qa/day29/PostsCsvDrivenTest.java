package ru.qa.day29;

import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import ru.qa.dto.CreatePostRequest;
import ru.qa.util.SchemaValidatorUtil;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static ru.qa.specs.ApiSpecs.*;

public class PostsCsvDrivenTest {
    @Feature("Post API")
    @Story("Create user")
    @DisplayName("Create post")
    @ParameterizedTest(name = " > {0}")
    @CsvFileSource(resources = "/testData/UserTestCase/posts.csv", numLinesToSkip = 1)
    @Step("POST /posts → title={1}, userId={0}")
    void shouldCreatePostsFromCsv(String scenario, int userId, String title, String body) {
        CreatePostRequest request = CreatePostRequest.builder()
                                                     .title(title)
                                                     .body(body != null ? body : "")
                                                     .userId(userId)
                                                     .build();

        Response response =  given()
                .spec(baseRequestSpec())
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/posts")
                .then()
                .spec(successResponseSpec())
                .body("title", equalTo(title))
                .extract().response();

        SchemaValidatorUtil.checkSavedJsonSchema(response.asString(), "schemas/post-schema.json");
    }
}
