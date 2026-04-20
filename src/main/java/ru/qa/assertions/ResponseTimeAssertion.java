package ru.qa.assertions;

import io.restassured.response.Response;

public class ResponseTimeAssertion extends BaseAssertion<Response> {

    private long maxTime;

    public ResponseTimeAssertion(Response actual, long maxTime) {
        super(actual);
        this.maxTime = maxTime;
    }

    @Override
    public void verify() {
        long actualTime = actual.time();
        if (actualTime > maxTime) {
            throw new AssertionError(
                    "Expected response time < " + maxTime + "ms but was " + actualTime + "ms"
            );
        }
    }

    @Override
    protected String getDescription() {
        return "Response time should be < " + maxTime + "ms";
    }
}