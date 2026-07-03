package ru.qa.day29;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import ru.qa.dto.CreatePostRequest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static ru.qa.specs.ApiSpecs.*;

public class PostsCsvDrivenTest {

    @ParameterizedTest(name = "Create post: title={1}, userId={0}")
    @CsvFileSource(resources = "/testData/UserTestCase/posts.csv", numLinesToSkip = 1)
    @DisplayName("Create posts from CSV dataset")
    void shouldCreatePostsFromCsv(int userId, String title, String body) {
        CreatePostRequest request = CreatePostRequest.builder()
                                                     .title(title)
                                                     .body(body)
                                                     .userId(userId)
                                                     .build();

        given()
                .spec(baseRequestSpec())
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/posts")
                .then()
                .spec(successResponseSpec())
                .body("title", equalTo(title));
    }
}
