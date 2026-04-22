package ru.qa.day18;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.qa.base.BaseApiTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class HeadersTest extends BaseApiTest {

    @DisplayName("GET: should send customer header")
    @Test
    void shouldSendCustomerHeader() {
        given()
                .spec(requestSpec)
                .header("X-Custom-Header", "test-value")
                .when()
                .get(getBasePath())
                .then()
                .statusCode(200);
    }

@DisplayName("GET: response should have Content-type header")
@Test
void shouldReceiveContentTypeHeader() {
    given()
            .spec(requestSpec)
            .when()
            .get(getBasePath() + "/1")
            .then()
            .header("Content-Type", containsString("json"));
}

@DisplayName("GET: Multiple headers get has 200 status code")
@Test
void shouldReceiveMultipleHeaders() {
        given()
                .spec(requestSpec)
                .header("Accept", "application/json")
                .header("User-Agent", "RestAssured-Tests")
                .header("X-Request-ID", "12345")
                .when()
                .get(getBasePath() + "/1")
                .then()
                .statusCode(200);
}

    @Override
    protected String getBasePath() {
        return "/posts";
    }
}
