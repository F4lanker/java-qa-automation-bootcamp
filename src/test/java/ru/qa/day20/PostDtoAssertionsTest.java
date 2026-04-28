package ru.qa.day20;

import org.junit.jupiter.api.Test;
import ru.qa.assertions.PostDtoAssertions;
import ru.qa.base.BaseApiTest;
import ru.qa.dto.PostDto;
import ru.qa.specs.ApiSpecs;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PostDtoAssertionsTest extends BaseApiTest {

    @Test
    void shouldhaveAllfieldsValid() {
        List<PostDto> post = given()
                .spec(ApiSpecs.loggingRequestSpec())
                .queryParam("usedId", 1)
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
                    .spec(ApiSpecs.loggingRequestSpec())
                    .queryParam("userId", userId)
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
