package ru.qa.specs;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.Filter;
import io.restassured.http.*;
import io.restassured.mapper.ObjectMapper;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import io.restassured.specification.*;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.security.KeyStore;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.lessThan;
import static ru.qa.base.ApiTestConfig.BASE_URL;
import static ru.qa.config.constants.ApiConfig.*;

public final class ApiSpecs implements RequestSpecification {

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

    @Override
    public RequestSpecification body(String body) {
        return null;
    }

    @Override
    public RequestSpecification body(byte[] body) {
        return null;
    }

    @Override
    public RequestSpecification body(File body) {
        return null;
    }

    @Override
    public RequestSpecification body(InputStream body) {
        return null;
    }

    @Override
    public RequestSpecification body(Object object) {
        return null;
    }

    @Override
    public RequestSpecification body(Object object, ObjectMapper mapper) {
        return null;
    }

    @Override
    public RequestSpecification body(Object object, ObjectMapperType mapperType) {
        return null;
    }

    @Override
    public RedirectSpecification redirects() {
        return null;
    }

    @Override
    public RequestSpecification cookies(String firstCookieName, Object firstCookieValue, Object... cookieNameValuePairs) {
        return null;
    }

    @Override
    public RequestSpecification cookies(Map<String, ?> cookies) {
        return null;
    }

    @Override
    public RequestSpecification cookies(Cookies cookies) {
        return null;
    }

    @Override
    public RequestSpecification cookie(String cookieName, Object value, Object... additionalValues) {
        return null;
    }

    @Override
    public RequestSpecification cookie(String cookieName) {
        return null;
    }

    @Override
    public RequestSpecification cookie(Cookie cookie) {
        return null;
    }

    @Override
    public RequestSpecification params(String firstParameterName, Object firstParameterValue, Object... parameterNameValuePairs) {
        return null;
    }

    @Override
    public RequestSpecification params(Map<String, ?> parametersMap) {
        return null;
    }

    @Override
    public RequestSpecification param(String parameterName, Object... parameterValues) {
        return null;
    }

    @Override
    public RequestSpecification param(String parameterName, Collection<?> parameterValues) {
        return null;
    }

    @Override
    public RequestSpecification queryParams(String firstParameterName, Object firstParameterValue, Object... parameterNameValuePairs) {
        return null;
    }

    @Override
    public RequestSpecification queryParams(Map<String, ?> parametersMap) {
        return null;
    }

    @Override
    public RequestSpecification queryParam(String parameterName, Object... parameterValues) {
        return null;
    }

    @Override
    public RequestSpecification queryParam(String parameterName, Collection<?> parameterValues) {
        return null;
    }

    @Override
    public RequestSpecification formParams(String firstParameterName, Object firstParameterValue, Object... parameterNameValuePairs) {
        return null;
    }

    @Override
    public RequestSpecification formParams(Map<String, ?> parametersMap) {
        return null;
    }

    @Override
    public RequestSpecification formParam(String parameterName, Object... parameterValues) {
        return null;
    }

    @Override
    public RequestSpecification formParam(String parameterName, Collection<?> parameterValues) {
        return null;
    }

    @Override
    public RequestSpecification pathParam(String parameterName, Object parameterValue) {
        return null;
    }

    @Override
    public RequestSpecification pathParams(String firstParameterName, Object firstParameterValue, Object... parameterNameValuePairs) {
        return null;
    }

    @Override
    public RequestSpecification pathParams(Map<String, ?> parameterNameValuePairs) {
        return null;
    }

    @Override
    public RequestSpecification config(RestAssuredConfig config) {
        return null;
    }

    @Override
    public RequestSpecification keyStore(String pathToJks, String password) {
        return null;
    }

    @Override
    public RequestSpecification keyStore(File pathToJks, String password) {
        return null;
    }

    @Override
    public RequestSpecification trustStore(String path, String password) {
        return null;
    }

    @Override
    public RequestSpecification trustStore(File path, String password) {
        return null;
    }

    @Override
    public RequestSpecification trustStore(KeyStore trustStore) {
        return null;
    }

    @Override
    public RequestSpecification keyStore(KeyStore keyStore) {
        return null;
    }

    @Override
    public RequestSpecification relaxedHTTPSValidation() {
        return null;
    }

    @Override
    public RequestSpecification relaxedHTTPSValidation(String protocol) {
        return null;
    }

    @Override
    public RequestSpecification headers(String firstHeaderName, Object firstHeaderValue, Object... headerNameValuePairs) {
        return null;
    }

    @Override
    public RequestSpecification headers(Map<String, ?> headers) {
        return null;
    }

    @Override
    public RequestSpecification headers(Headers headers) {
        return null;
    }

    @Override
    public RequestSpecification header(String headerName, Object headerValue, Object... additionalHeaderValues) {
        return null;
    }

    @Override
    public RequestSpecification header(Header header) {
        return null;
    }

    @Override
    public RequestSpecification contentType(ContentType contentType) {
        return null;
    }

    @Override
    public RequestSpecification contentType(String contentType) {
        return null;
    }

    @Override
    public RequestSpecification noContentType() {
        return null;
    }

    @Override
    public RequestSpecification accept(ContentType contentType) {
        return null;
    }

    @Override
    public RequestSpecification accept(String mediaTypes) {
        return null;
    }

    @Override
    public RequestSpecification multiPart(MultiPartSpecification multiPartSpecification) {
        return null;
    }

    @Override
    public RequestSpecification multiPart(File file) {
        return null;
    }

    @Override
    public RequestSpecification multiPart(String controlName, File file) {
        return null;
    }

    @Override
    public RequestSpecification multiPart(String controlName, File file, String mimeType) {
        return null;
    }

    @Override
    public RequestSpecification multiPart(String controlName, Object object) {
        return null;
    }

    @Override
    public RequestSpecification multiPart(String controlName, Object object, String mimeType) {
        return null;
    }

    @Override
    public RequestSpecification multiPart(String controlName, String filename, Object object, String mimeType) {
        return null;
    }

    @Override
    public RequestSpecification multiPart(String controlName, String fileName, byte[] bytes) {
        return null;
    }

    @Override
    public RequestSpecification multiPart(String controlName, String fileName, byte[] bytes, String mimeType) {
        return null;
    }

    @Override
    public RequestSpecification multiPart(String controlName, String fileName, InputStream stream) {
        return null;
    }

    @Override
    public RequestSpecification multiPart(String controlName, String fileName, InputStream stream, String mimeType) {
        return null;
    }

    @Override
    public RequestSpecification multiPart(String controlName, String contentBody) {
        return null;
    }

    @Override
    public RequestSpecification multiPart(String controlName, String contentBody, String mimeType) {
        return null;
    }

    @Override
    public AuthenticationSpecification auth() {
        return null;
    }

    @Override
    public RequestSpecification csrf(String csrfTokenPath) {
        return null;
    }

    @Override
    public RequestSpecification csrf(String csrfTokenPath, String csrfFieldName) {
        return null;
    }

    @Override
    public RequestSpecification disableCsrf() {
        return null;
    }

    @Override
    public RequestSpecification port(int port) {
        return null;
    }

    @Override
    public RequestSpecification spec(RequestSpecification requestSpecificationToMerge) {
        return null;
    }

    @Override
    public RequestSpecification sessionId(String sessionIdValue) {
        return null;
    }

    @Override
    public RequestSpecification sessionId(String sessionIdName, String sessionIdValue) {
        return null;
    }

    @Override
    public RequestSpecification urlEncodingEnabled(boolean isEnabled) {
        return null;
    }

    @Override
    public RequestSpecification filter(Filter filter) {
        return null;
    }

    @Override
    public RequestSpecification filters(List<Filter> filters) {
        return null;
    }

    @Override
    public RequestSpecification filters(Filter filter, Filter... additionalFilter) {
        return null;
    }

    @Override
    public RequestSpecification noFilters() {
        return null;
    }

    @Override
    public <T extends Filter> RequestSpecification noFiltersOfType(Class<T> filterType) {
        return null;
    }

    @Override
    public RequestLogSpecification log() {
        return null;
    }

    @Override
    public ResponseSpecification response() {
        return null;
    }

    @Override
    public RequestSpecification and() {
        return null;
    }

    @Override
    public RequestSpecification with() {
        return null;
    }

    @Override
    public ResponseSpecification then() {
        return null;
    }

    @Override
    public ResponseSpecification expect() {
        return null;
    }

    @Override
    public RequestSpecification when() {
        return null;
    }

    @Override
    public RequestSpecification given() {
        return null;
    }

    @Override
    public RequestSpecification that() {
        return null;
    }

    @Override
    public RequestSpecification request() {
        return null;
    }

    @Override
    public RequestSpecification baseUri(String baseUri) {
        return null;
    }

    @Override
    public RequestSpecification basePath(String basePath) {
        return null;
    }

    @Override
    public RequestSpecification proxy(String host, int port) {
        return null;
    }

    @Override
    public RequestSpecification proxy(String host) {
        return null;
    }

    @Override
    public RequestSpecification proxy(int port) {
        return null;
    }

    @Override
    public RequestSpecification proxy(String host, int port, String scheme) {
        return null;
    }

    @Override
    public RequestSpecification proxy(URI uri) {
        return null;
    }

    @Override
    public RequestSpecification proxy(ProxySpecification proxySpecification) {
        return null;
    }

    @Override
    public Response get(String path, Object... pathParams) {
        return null;
    }

    @Override
    public Response get(String path, Map<String, ?> pathParams) {
        return null;
    }

    @Override
    public Response post(String path, Object... pathParams) {
        return null;
    }

    @Override
    public Response post(String path, Map<String, ?> pathParams) {
        return null;
    }

    @Override
    public Response put(String path, Object... pathParams) {
        return null;
    }

    @Override
    public Response put(String path, Map<String, ?> pathParams) {
        return null;
    }

    @Override
    public Response delete(String path, Object... pathParams) {
        return null;
    }

    @Override
    public Response delete(String path, Map<String, ?> pathParams) {
        return null;
    }

    @Override
    public Response head(String path, Object... pathParams) {
        return null;
    }

    @Override
    public Response head(String path, Map<String, ?> pathParams) {
        return null;
    }

    @Override
    public Response patch(String path, Object... pathParams) {
        return null;
    }

    @Override
    public Response patch(String path, Map<String, ?> pathParams) {
        return null;
    }

    @Override
    public Response options(String path, Object... pathParams) {
        return null;
    }

    @Override
    public Response options(String path, Map<String, ?> pathParams) {
        return null;
    }

    @Override
    public Response get(URI uri) {
        return null;
    }

    @Override
    public Response post(URI uri) {
        return null;
    }

    @Override
    public Response put(URI uri) {
        return null;
    }

    @Override
    public Response delete(URI uri) {
        return null;
    }

    @Override
    public Response head(URI uri) {
        return null;
    }

    @Override
    public Response patch(URI uri) {
        return null;
    }

    @Override
    public Response options(URI uri) {
        return null;
    }

    @Override
    public Response get(URL url) {
        return null;
    }

    @Override
    public Response post(URL url) {
        return null;
    }

    @Override
    public Response put(URL url) {
        return null;
    }

    @Override
    public Response delete(URL url) {
        return null;
    }

    @Override
    public Response head(URL url) {
        return null;
    }

    @Override
    public Response patch(URL url) {
        return null;
    }

    @Override
    public Response options(URL url) {
        return null;
    }

    @Override
    public Response get() {
        return null;
    }

    @Override
    public Response post() {
        return null;
    }

    @Override
    public Response put() {
        return null;
    }

    @Override
    public Response delete() {
        return null;
    }

    @Override
    public Response head() {
        return null;
    }

    @Override
    public Response patch() {
        return null;
    }

    @Override
    public Response options() {
        return null;
    }

    @Override
    public Response request(Method method) {
        return null;
    }

    @Override
    public Response request(String method) {
        return null;
    }

    @Override
    public Response request(Method method, String path, Object... pathParams) {
        return null;
    }

    @Override
    public Response request(String method, String path, Object... pathParams) {
        return null;
    }

    @Override
    public Response request(Method method, URI uri) {
        return null;
    }

    @Override
    public Response request(Method method, URL url) {
        return null;
    }

    @Override
    public Response request(String method, URI uri) {
        return null;
    }

    @Override
    public Response request(String method, URL url) {
        return null;
    }
}

