package ru.qa.client;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static ru.qa.config.constansts.ApiConfig.BASE_URL;


/**
 * Flexible API client demonstrating method overloading.
 */
public class FlexibleApiClient {

    private RequestSpecification spec;

    public FlexibleApiClient(String basePath) {
        spec = given()
                .baseUri(BASE_URL)
                .basePath(basePath);
    }

    // Overload 1: GET без параметров
    public Response get(String endpoint) {
        return spec.when().get(endpoint);
    }

    // Overload 2: GET с одним query параметром
    public Response get(String endpoint, String paramName, Object paramValue) {
        return spec
                .queryParam(paramName, paramValue)
                .when().get(endpoint);
    }

    // Overload 3: GET с несколькими параметрами (Map)
    public Response get(String endpoint, Map<String, Object> queryParams) {
        return spec
                .queryParams(queryParams)
                .when().get(endpoint);
    }

    // Overload 4: GET с path параметром
    public Response getById(String endpoint, int id) {
        return spec.when().get(endpoint + "/" + id);
    }

    // Overload 5: POST с телом (Object)
    public Response post(String endpoint, Object body) {
        return spec
                .body(body)
                .when().post(endpoint);
    }

    // Overload 6: POST с телом (Map)
    public Response post(String endpoint, Map<String, Object> body) {
        return spec
                .body(body)
                .when().post(endpoint);
    }
}