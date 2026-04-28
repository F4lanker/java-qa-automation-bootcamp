package ru.qa.day20;

import org.junit.jupiter.api.Test;
import ru.qa.assertions.PostDtoAssertions;
import ru.qa.base.BaseApiTest;
import ru.qa.dto.PostDto;
import ru.qa.specs.ApiSpecs;

import java.util.List;

import static io.restassured.RestAssured.given;
import static ru.qa.specs.ApiSpecs.*;


public class PostDtoAssertionsTest extends BaseApiTest {

    @Test
    void shouldHaveAllFieldsValid() {
        List<PostDto> post = given()
                .spec(loggingRequestSpec())
                .queryParam("userId", 1)
                .when()
                .get(getBasePath())
                        .then()
                        .spec(ApiSpecs.successResponseSpec())
                        .extract().jsonPath().getList(".", PostDto.class);

        post.forEach(PostDtoAssertions::assertValidPost);
    }

    @Test
    void shouldHaveValidName(){

        for (int userId = 1; userId < 10; userId++) {
            List<PostDto> posts = given()
                    .spec(loggingRequestSpec())
                    .queryParam("userId", userId)
                    .when()
                    .get(getBasePath())
                    .then()
                    .spec(ApiSpecs.successResponseSpec())
                    .extract().jsonPath().getList(".", PostDto.class);
            for (PostDto post : posts) {
                PostDtoAssertions.assertValidPost(post);
                PostDtoAssertions.assertUserPost(post, userId);
            };
        }
    }

    @Override
    protected String getBasePath() {
        return "/posts";
    }
}
