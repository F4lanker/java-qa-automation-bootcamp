package ru.qa.day18;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.qa.base.BaseApiTest;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;

public class CombinedParamsTest extends BaseApiTest {

    @DisplayName("GET: combibe booth header and query params")
    @Test
    void shouldCombineQueryParamsAndHeaders() {
        int userId = 1;
        int limit = 5;

        given()
                .spec(requestSpec)
                .when()
                .header("Accept", "application/json")
                .queryParam("userId", userId)
                .queryParam("_limit", limit)
                .get(getBasePath())
                .then()
                .body("", hasSize(limit))       //check the list size is the same as limit declared
                .header("Content-Type", containsString("json")); //check the response has "Content-Type" - json
    }

@DisplayName("GET: query params used from Map")
@Test
void shouldUseMapForQueryParams(){
    Map<String, Object> params = new HashMap<>();
    params.put("userId", 1);
    params.put("_limit", 10);

    given()
            .spec(requestSpec)
            .when()
            .queryParams(params)
            .get(getBasePath())
            .then()
            .body("", hasSize(10));
}

    @DisplayName("GET: headers used from Map")
    @Test
    void shouldUseMapForHeaders(){
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        headers.put("X-Custom-Header", "test");

        given()
        .spec(requestSpec)
        .when()
        .headers(headers)
        .get(getBasePath())
        .then()
        .statusCode(200);
    }
    @Override
    protected String getBasePath() {
        return "/posts";
    }
}
