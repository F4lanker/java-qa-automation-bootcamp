package ru.qa.filter;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

import java.io.PrintStream;

public class CustomRequestLoggingFilter implements Filter {
    private final PrintStream output;

    // Default constructor — write to System.out
    public CustomRequestLoggingFilter() {
        this.output = System.out;
    }

    // Custom stream constructor — write upon request
    public CustomRequestLoggingFilter(PrintStream output) {
        this.output = output;
    }

    @Override
    public Response filter(FilterableRequestSpecification requestSpec,
                           FilterableResponseSpecification responseSpec,
                           FilterContext ctx) {

        // Logging method and URL
        output.printf("-> %s, %s%n", requestSpec.getMethod(), requestSpec.getURI());

        //Track response starting
        long timeStart = System.currentTimeMillis();

        // Perform real request and get the response
        Response response = ctx.next(requestSpec, responseSpec);
        long duration = System.currentTimeMillis() - timeStart;

        //Logging Status code and response time
        output.printf("<- %d (%dms)%n", response.getStatusCode(), duration);

        return response; // return whole chain
    }
}
