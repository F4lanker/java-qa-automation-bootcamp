package ru.qa.day18;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.qa.base.BaseApiTest;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;

public class QueryParamsTest extends BaseApiTest {
    /**
     * Tests implements query param
     */
    @DisplayName("queryParam: userId should return user list with requested ID-s")
    @Test
    public void shouldFilterPostsByUserId() {
        int expectedId = 1;
        List<Integer> list = given()
                .spec(requestSpec)
                .queryParam("userId", 1)
                .when()
                .get(getBasePath())
                .then()
                .spec(responseSpec)
                .extract().jsonPath().getList("userId", Integer.class);

        if (list != null) {
            int size = list.size();
            for (Integer integer : list) {
                Assertions.assertEquals(expectedId, integer.intValue());
            }
        }
    }

    @DisplayName("queryParam: limit should limit amount of output result to selected number")
    @Test
    public void shouldLimitResultToSelectedNumber() {
        int expectedLimit = 5;

         given()
                .spec(requestSpec)
                .queryParam("_limit", 5)
                .when()
                .get(getBasePath())
                .then()
                .spec(responseSpec)
                .body("", hasSize(expectedLimit));
    }

    @Override
    protected String getBasePath() {
        return "/posts";
    }
}
