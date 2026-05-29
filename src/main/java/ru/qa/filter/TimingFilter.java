package ru.qa.filter;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class TimingFilter implements Filter {
    private final long thresholdMs;

    public TimingFilter(long thresholdMs) {
        this.thresholdMs = thresholdMs;
    }

    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {

        //Track response starting
        long timeStart = System.currentTimeMillis();

        // Perform real request and get the response
        Response response = ctx.next(requestSpec, responseSpec);
        long duration = System.currentTimeMillis() - timeStart;

        if (duration > thresholdMs) {
            throw new AssertionError(
                    String.format("Request exceeded threshold: %dms > %dms", duration, thresholdMs)
            );

        }
        return response;
    }

}
