package ru.qa.day18;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.qa.base.BaseApiTest;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;

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

        assertFalse(list.isEmpty(), "Response should contain posts");
        assertTrue(list.stream().allMatch(id -> id == expectedId), "All posts should have expected ID");
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
                .body("", hasSize(expectedLimit));      //check that elements in match limits requested in query perams
    }

    @DisplayName("queryParam: should sort Desc")
    @Test
    public void shouldSortByIdDesc() {
        List<Integer> list = given()
                .spec(requestSpec)
                .queryParam("_sort", "id")
                .queryParam("_order", "desc")
                .when()
                .get(getBasePath())
                .then()
                .spec(responseSpec)
                .extract().jsonPath().getList("id", Integer.class);

        assertTrue(list.get(0) > list.get(list.size() - 1));        //check that the first elements ID is greater than the last one
    }

    @DisplayName("queryParam: combinations of params works correct")
    @Test
    void shouldCombineMultipleQueryParams() {
        int limit = 3;
        int userId = 1;
        List<Integer> list = given()
                .spec(requestSpec)
                .queryParam("userId", userId)
                .queryParam("_limit", limit)
                .when()
                .get(getBasePath())
                .then()
                .spec(responseSpec)
                .extract().jsonPath().getList("userId", Integer.class);

        assertAll(
                () -> assertTrue(list.stream().allMatch(id -> id == userId), "All posts should have expected ID"),
                () -> assertEquals(limit, list.size()));                  //check limits has the requested size

    }

    @Override
    protected String getBasePath() {
        return "/posts";
    }
}

