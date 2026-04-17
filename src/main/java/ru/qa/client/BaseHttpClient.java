package ru.qa.client;

import io.restassured.response.Response;

import java.util.Map;

/**
 * Http client enhanced logging
 */
public abstract class BaseHttpClient {

    protected abstract String getEndpoints();

    /**
     * Execute GET request
     * Subclasses can be overwritten to add custom logic
     */
    public Response get(String path) {
        System.out.println(("[GET}" + getEndpoints() + path));
        return executeGet(path);
    }

    /**
     * Execute Post request
     * Subclasses can be overwritten to add custom logic
     */

    public Response post(String path, Object body) {
        System.out.println("[POST}" + getEndpoints() + path)
               return executePost(path, body) ;
    }

    /**
     * Override this to customize Get behaviour
     */
    protected abstract Response executeGet(String path);

    /**
     * Override this to customize Post behavior
     */
    protected abstract Response executePost(String path, Object body);

}
