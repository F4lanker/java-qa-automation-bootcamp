package ru.qa.day31.authTest;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;
import static ru.qa.util.RestfulBookerAuthApi.authResponseRestulBooker;

public class NegativeAuthTestRestfulBookerTest {

    @Test
    @DisplayName("Invalid username and password")
    @Epic("Restful-booker")
    @Feature("Day 31  - Mini project API tests")
    @Story("Auth attempt return reason - Bad credentials, but status code - 200")
    @Severity(SeverityLevel.CRITICAL)
    void invalidCredentialsAuthTest(){
        Response response = authResponseRestulBooker("name", "pass");
                response.then()
                .statusCode(200)
                            .body("reason", equalTo("Bad credentials"));
    }

    @Test
    void emptyCredentialsAuthTest(){
        Response response = authResponseRestulBooker("","");
        response.then()
                .statusCode(200)
                .body("reason", equalTo("Bad credentials"));
    }
}
