package client;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static ru.qa.config.constansts.ApiConfig.BASE_URL;

/**
 * Base API client with Template Method pattern.
 */
public abstract class BaseApiClient {
    protected RequestSpecification spec;

    public BaseApiClient() {
        spec = new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setBasePath(getEndpoint())
                .build();
    }

    /**
     * Each client specifies its endpoint.
     */
    protected abstract String getEndpoint();

    /**
     * Template method for standard GET flow.
     */
    public <T> T getById(int id, Class<T> responseClass) {
        logRequest("GET", id);
        T response = sendGetRequest(id, responseClass);
        logResponse(response);
        return response;
    }

    protected abstract <T> T sendGetRequest(int id, Class<T> responseClass);

    private void logRequest(String method, int id) {
        System.out.println("[API] " + method + " " + getEndpoint() + "/" + id);
    }

    private void logResponse(Object response) {
        System.out.println("[API] Response: " + response);
    }
}