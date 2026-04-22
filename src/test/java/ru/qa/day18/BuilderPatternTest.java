package ru.qa.day18;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import ru.qa.base.BaseApiTest;
import ru.qa.builder.ApiRequestBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.qa.config.constansts.ApiConfig.BASE_URL;

public class BuilderPatternTest extends BaseApiTest {
    @Test
    void shouldUseBuilder() {
        Response response = ApiRequestBuilder.create()
                                             .baseUrl(BASE_URL)
                                             .endpoint(getBasePath())
                                             .queryParam("userId", 1)
                                             .queryParam("_limit", 5)
                                             .header("Accept", "application/json")
                                             .enableLogging()
                                             .send();

        assertEquals(200, response.statusCode());
        assertEquals(5, response.jsonPath().getList("$").size());
    }

    @Override
    protected String getBasePath() {
        return "/posts";
    }
}
