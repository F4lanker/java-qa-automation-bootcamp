package ru.qa.day17;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.qa.assertions.BaseAssertion;
import ru.qa.assertions.ResponseTimeAssertion;
import ru.qa.assertions.StatusCodeAssertion;
import ru.qa.client.BaseHttpClient;
import ru.qa.client.LoggingHttpClient;
import ru.qa.client.RetryHttpClient;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.qa.base.ApiTestConfig.BASE_URL;

public class PolymorphismTest {

    @Test
    @DisplayName("Polymorphism: LoggingHttpClient")
    void testLoggingClient() {
        BaseHttpClient client = new LoggingHttpClient();  // Полиморфизм

        Response response = client.get("/1");

        assertEquals(200, response.statusCode());
    }

    @Test
    @DisplayName("Polymorphism: RetryHttpClient")
    void testRetryClient() {
        BaseHttpClient client = new RetryHttpClient();  // Полиморфизм

        Response response = client.get("/1");

        assertEquals(200, response.statusCode());
    }

    @Test
    @DisplayName("Polymorphism: можно менять реализацию динамически")
    void testDynamicPolymorphism() {
        BaseHttpClient client;

        // В тесте используем LoggingClient
        client = new LoggingHttpClient();
        Response response1 = client.get("/1");
        assertEquals(200, response1.statusCode());

        // В production используем RetryClient
        client = new RetryHttpClient();
        Response response2 = client.get("/1");
        assertEquals(200, response2.statusCode());
    }

    @Test
    void testPolymorphicAssertions() {
        Response response = given().get(BASE_URL + "/posts/1");

        // Полиморфизм — разные assertions, одинаковый интерфейс
        BaseAssertion<Response> assertion1 = new StatusCodeAssertion(response, 200);
        BaseAssertion<Response> assertion2 = new ResponseTimeAssertion(response, 2000L);

        assertion1.assertThat();  // Проверка статуса
        assertion2.assertThat();  // Проверка времени
    }
}