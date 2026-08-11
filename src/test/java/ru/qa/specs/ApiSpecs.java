package ru.qa.specs;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.lessThan;
import static ru.qa.base.ApiTestConfig.BASE_URL;
import static ru.qa.config.constants.ApiConfig.*;

public final class ApiSpecs {

    private ApiSpecs() {
        throw new AssertionError("Utility class");
    }

    /**
     * Base spec for all requests
     */


    public static RequestSpecification baseRequestSpec() {
        return baseRequestSpec(BASE_URL);  // ✅ Делегируем в новый метод
    }

    // Custom baseUri method:
    public static RequestSpecification baseRequestSpec(String baseUrl) {
        return new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .addHeader("X-Test-Source", "RestAssured-Bootcamp")
                .addFilter(new AllureRestAssured())
                .build();
    }

    // Base request for https://httpbin.org
    public static RequestSpecification httpBinRequestSpec() {
        return baseRequestSpec(HTTPBIN_URL);
    }

    // Base request for https://reqres.in
    public static RequestSpecification reqresSpec() {
        return baseRequestSpec(REQRES_URL);
    }

    public static RequestSpecification rstflBookerReqSpec() {
        return baseRequestSpec(RESTFULBKR_URL);
    }

    /**
     * Logging spec (for debug).
     */
    public static RequestSpecification loggingRequestSpec() {
        return new RequestSpecBuilder()
                .addRequestSpecification(baseRequestSpec())  // ✅ Наследуем базовую
                .log(io.restassured.filter.log.LogDetail.ALL)
                .build();
    }

    public static RequestSpecification loggingRequestSpec(String baseUrl) {
        return new RequestSpecBuilder()
                .addRequestSpecification(baseRequestSpec(baseUrl))  // ✅ Наследуем базовую
                .log(io.restassured.filter.log.LogDetail.ALL)
                .build();
    }

    /**
     * Auth request spec
     */
    public static RequestSpecification authRequestSpec(String baseUrl, String token) {
        return new RequestSpecBuilder()
                .addRequestSpecification(baseRequestSpec(baseUrl))
                .addHeader("Authorization", "Bearer " + token)
                .build();
    }

    /**
     * Auth request spec via cookies
     */
    public static RequestSpecification authRequestSpecCookie(String baseUrl, String token) {
        return new RequestSpecBuilder()
                .addRequestSpecification(baseRequestSpec(baseUrl))
                .addCookie("token", token)
                .addHeader("Content-Type", "application/json")
                .build();
    }


    /**
     * Base spec for success response (2xx).
     */
    public static ResponseSpecification successResponseSpec() {
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectStatusCode(201)
                .expectResponseTime(lessThan(2000L))
                .build();
    }

    /**
     * Base spec for created (201).
     */
    public static ResponseSpecification createdResponseSpec() {
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectStatusCode(201)
                .build();
    }

    /**
     * Response spec for client errors (4xx).
     */
    public static ResponseSpecification clientErrorResponseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(org.hamcrest.Matchers.greaterThanOrEqualTo(400))
                .expectStatusCode(org.hamcrest.Matchers.lessThan(500))
                .build();
    }

    /**
     * Client error response (404).
     */
    public static ResponseSpecification notFoundResponseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(404)
                .build();
    }
}

