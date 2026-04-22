package ru.qa.day18;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.qa.base.BaseApiTest;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;

public class CombinedParamsTest extends BaseApiTest {

    @DisplayName("GET: combine both header and query params")
    @Test
    void shouldCombineQueryParamsAndHeaders() {
        int userId = 1;
        int limit = 5;

        given()
                .spec(requestSpec)
                .header("Accept", "application/json")
                .queryParam("userId", userId)
                .queryParam("_limit", limit)
                .when()
                .get(getBasePath())
                .then()
                .body("", hasSize(limit))       //check the list size is the same as limit declared
                .header("Content-Type", containsString("json")); //check the response has "Content-Type" - json
    }

@DisplayName("GET: query params used from Map")
@Test
void shouldUseMapForQueryParams(){
    Map<String, Object> params = Map.of(
            "userId", 1,
            "_limit", 10);

    given()
            .spec(requestSpec)
            .queryParams(params)
            .when()
            .get(getBasePath())
            .then()
            .body("", hasSize(10));
}

    @DisplayName("GET: headers used from Map")
    @Test
    void shouldUseMapForHeaders(){
        Map<String, String> headers = Map.of(
       "Accept", "application/json",
       "X-Custom-Header", "test");

        given()
        .spec(requestSpec)
        .headers(headers)
        .when()
        .get(getBasePath())
        .then()
        .statusCode(200);
    }
    @Override
    protected String getBasePath() {
        return "/posts";
    }
}
