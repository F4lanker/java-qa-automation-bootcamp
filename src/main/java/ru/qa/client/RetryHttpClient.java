package ru.qa.client;

import io.restassured.response.Response;
/**
 * HTTP client with retry logic on failure.
 */

public class RetryHttpClient extends BaseHttpClient {
    @Override
    protected String getEndpoints() {
        return "";
    }

    @Override
    protected Response executeGet(String path) {
        return null;
    }

    @Override
    protected Response executePost(String path, Object body) {
        return null;
    }
}
