package ru.qa.filter;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class RequestLoggingFilter implements Filter {
    @Override
    public Response filter(FilterableRequestSpecification requestSpec,
                           FilterableResponseSpecification responseSpec,
                           FilterContext ctx) {

        // Logging method and URL
        System.out.printf("-> %s, %s%n", requestSpec.getMethod(), requestSpec.getURI());

        //Track response starting
        long timeStart = System.currentTimeMillis();

        // Perform real request and get the response
        Response response = ctx.next(requestSpec, responseSpec);
        long duration = System.currentTimeMillis() - timeStart;

        //Logging Status code and response time
        System.out.printf("<- %d (%dms)%n", response.getStatusCode(), duration);

        return response; // return whole chain
    }
}
