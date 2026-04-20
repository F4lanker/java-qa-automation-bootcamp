package ru.qa.client;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static ru.qa.config.constansts.ApiConfig.BASE_URL;

/**
 * Http client with enhanced logging
 */
public class LoggingHttpClient extends BaseHttpClient {
    @Override
    protected String getEndpoints() {
        return "/posts";
    }

    @Override
    protected Response executeGet(String path) {
        System.out.println("[Logging] Sending Get request...");
        Response response = given()
                .baseUri(BASE_URL)
                .basePath(getEndpoints())
                .when().get(path);
        System.out.println("[Logging] Response code: " + response.statusCode());
        return response;
    }

    @Override
    protected Response executePost(String path, Object body) {
        System.out.println("[LoggingClient] Sending POST request with body: " + body);

        Response response = given()
                .baseUri(BASE_URL)
                .basePath(getEndpoints())
                .body(body)
                .when().post(path);

        System.out.println("[LoggingClient] Response code: " + response.statusCode());
        return response;
    }
}
