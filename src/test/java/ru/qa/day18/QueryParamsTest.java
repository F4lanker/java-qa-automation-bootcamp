package ru.qa.day18;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.qa.base.BaseApiTest;

import java.util.List;

import static io.restassured.RestAssured.given;

public class QueryParamsTest extends BaseApiTest {
    /**
     * Tests implements query param
     */

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
            for (int i = 0; i < size; i++) {
                Assertions.assertEquals(expectedId, list.get(i).intValue());
            }
        }
    }

    @Override
    protected String getBasePath() {
        return "/posts";
    }
}
