package ru.qa.client;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static ru.qa.config.constansts.ApiConfig.BASE_URL;

/**
 * HTTP client with retry logic on failure.
 */

public class RetryHttpClient extends BaseHttpClient {

    private static final int MAX_RETRIES = 3;

    @Override
    protected String getEndpoints() {
        return "/posts";
    }

    @Override
    protected Response executeGet(String path) {
        return null;
    }

    @Override
    protected Response executePost(String path, Object body) {
        return executeWithRetry(()->
        given()
                .baseUri(BASE_URL)
                .basePath(getEndpoints())
                .body(body)
                .when().post(path)
        );
    }

    private Response executeWithRetry(java.util.function.Supplier<Response> action) {
        for (int i = 0; i < MAX_RETRIES; i++) {
            System.out.println("[RetryClient] Attempt " + i + "/" + MAX_RETRIES);

            Response response = action.get();
            if (response.getStatusCode() == HttpStatus.SC_OK || response.getStatusCode() == HttpStatus.SC_CREATED) {
                return response;
            }
            System.out.println("[RetryClient] Failed " + i + "/" + MAX_RETRIES);
        }
        throw new RuntimeException("Max retries exceeded");
    }
}
