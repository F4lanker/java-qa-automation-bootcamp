package ru.qa.specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.lessThan;
import static ru.qa.base.ApiTestConfig.BASE_URL;
import static ru.qa.config.constansts.ApiConfig.HTTPBIN_URL;
import static ru.qa.config.constansts.ApiConfig.REQRES_URL;

public class ApiSpecs {

    private ApiSpecs(){
        throw new AssertionError("Utility class");
    }

    /**
     * Базовая спецификация для всех запросов.
     */

    // Существующий метод — без изменений:
    public static RequestSpecification baseRequestSpec() {
        return baseRequestSpec(BASE_URL);  // ✅ Делегируем в новый метод
    }

    // Новый перегруженный метод с кастомным baseUri:
    public static RequestSpecification baseRequestSpec(String baseUrl) {
        return new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .setContentType(ContentType.JSON)
                .addHeader("X-Test-Source", "RestAssured-Bootcamp")
                .build();
    }
// Базовый запрос для https://httpbin.org
    public static RequestSpecification httpBinRequestSpec() {
        return baseRequestSpec(HTTPBIN_URL);
    }

// Базовый запрос для https://reqres.in
    public static RequestSpecification httpRequestSpec() {
        return baseRequestSpec(REQRES_URL);
    }

    /**
     * Спецификация с логированием (для отладки).
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
     * Спецификация с авторизацией (для будущих задач).
     */
    public static RequestSpecification authRequestSpec(String token) {
        return new RequestSpecBuilder()
                .addRequestSpecification(httpRequestSpec())
                .addHeader("Authorization", "Bearer " + token)
                .build();
    }



    /**
     * Базовая спецификация для успешных ответов (2xx).
     */
    public static ResponseSpecification successResponseSpec() {
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectStatusCode(200)
                .expectResponseTime(lessThan(2000L))
                .build();
    }

    /**
     * Спецификация для созданных ресурсов (201).
     */
    public static ResponseSpecification createdResponseSpec() {
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectStatusCode(201)
                .build();
    }

    /**
     * Спецификация для ошибок клиента (4xx).
     */
    public static ResponseSpecification clientErrorResponseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(org.hamcrest.Matchers.greaterThanOrEqualTo(400))
                .expectStatusCode(org.hamcrest.Matchers.lessThan(500))
                .build();
    }

    /**
     * Спецификация для ошибок клиента (404).
     */
    public static ResponseSpecification notFoundResponseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(404)
                .build();
    }
}

