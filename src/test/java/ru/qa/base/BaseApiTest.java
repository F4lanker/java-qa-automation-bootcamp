package ru.qa.base;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeEach;

import static org.hamcrest.Matchers.lessThan;
import static ru.qa.config.constansts.ApiConfig.BASE_URL;

/**
 * Base class for all API test
 * Provides common ReaquesSpecification and ResponseSpecification
 */

public abstract class BaseApiTest extends BaseTest {

    protected RequestSpecification requestSpec;
    protected ResponseSpecification responseSpec;

    /**
     * Returns the base path for this API test.
     * Each test must override this to specify its endpoint.
     *
     * @return base path (e.g., "/users", "/posts", "/todos")
     */

    protected abstract String getBasePath();

    @Override
    @BeforeEach
    public void setUpBase() {
        super.setUpBase(); // call the parents method first

        System.out.println("[BaseApiTest] API-specific setup");

        requestSpec = new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setContentType(ContentType.JSON)
                .addHeader("X-Test-Source", "RestAssured-Bootcamp")
                .log(LogDetail.METHOD)
                .log(LogDetail.URI)
                .log(LogDetail.HEADERS)
                .build();

        responseSpec = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectResponseTime(lessThan(2000L)) //all responses < 2 seconds
                .log(LogDetail.BODY)
                .log(LogDetail.STATUS)
                .build();
    }


}
