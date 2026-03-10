package ru.qa.base;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeEach;

import static ru.qa.day10.ApiConfig.BASE_URL;

/**
 * Base class for all API test
 * Provides common ReaquesSpecification and ResponseSpecification
 */

public abstract class BaseApiTest {

    protected RequestSpecification requestSpec;
    protected ResponseSpecification responseSpec;

    @BeforeEach
    public void setup() {
        requestSpec = new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setContentType(ContentType.JSON)
                .log(LogDetail.METHOD)
                .log(LogDetail.URI)
                .build();

        responseSpec = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .log(LogDetail.STATUS)
                .build();
    }


}
