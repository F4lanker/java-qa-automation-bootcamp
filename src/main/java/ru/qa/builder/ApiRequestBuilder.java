package ru.qa.builder;

import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiRequestBuilder {
    private String endpoint;
    private String baseUrl;
    private Map<String, Object> queryParams = new HashMap<>();
    private Map<String, String> headers = new HashMap<>();
    private boolean logRequest = false;

    public static ApiRequestBuilder create() {
        return new ApiRequestBuilder();
    }

    public ApiRequestBuilder endpoint(String endpoint) {
        this.endpoint = endpoint;
        return this;
    }

    public ApiRequestBuilder baseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    public ApiRequestBuilder queryParam(String key, Object value) {
        this.queryParams.put(key, value);
        return this;
    }

    public ApiRequestBuilder header(String key, String value) {
        this.headers.put(key, value);
        return this;
    }

    public ApiRequestBuilder enableLogging() {
        this.logRequest = true;
        return this;
    }

    public Response send() {
        // Начинаем строить запрос
        var request = given();

        // Включаем логирование, если нужно
        if (logRequest) request.log().all();
        if(baseUrl != null) request.baseUri(baseUrl);
        request.headers(headers);
        request.queryParams(queryParams);
        return request.get(endpoint);
    }
}
