package ru.qa.assertions;

import io.restassured.response.Response;

public class StatusCodeAssertion extends BaseAssertion<Response> {

    private int expected;

    public StatusCodeAssertion(Response actual, int expected) {
        super(actual);
        this.expected = expected;
    }

    @Override
    public void verify() {
        int actualCode = actual.statusCode();
        if (actualCode != expected) {
            throw new AssertionError(
                    "Expected status " + expected + " but got " + actualCode
            );
        }
    }

    @Override
    protected String getDescription() {
        return "Status code should be " + expected;
    }
}