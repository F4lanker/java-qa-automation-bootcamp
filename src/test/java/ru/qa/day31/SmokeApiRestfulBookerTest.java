package ru.qa.day31;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.qa.filter.TimingFilter;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static ru.qa.specs.ApiSpecs.*;

/**
 * Smoke API tests for https://restful-booker.herokuapp.com
 * GET /ping — simple health check with status code
 */
public class SmokeApiRestfulBookerTest {
    private static final int RESPONSE_TIMEOUT = 3000;


    @Test
    @DisplayName("Ping - health test")
    @Epic("Restful-booker")
    @Feature("Day 31  - Mini project API tests")
    @Story("Ping returns 201  Created")
    @Severity(SeverityLevel.CRITICAL)
    void pingSuccessResponse() {
        given()
                .spec(rstflBookerReqSpec())
                .filter(new TimingFilter(RESPONSE_TIMEOUT))
                .when()
                .get("/ping")
                .then()
                .log().all()
                .header("Content-Type", "text/plain; charset=utf-8")
                .statusCode(201)
                .body(equalTo("Created"));
    }
}
