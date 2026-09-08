package ru.qa.util;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import ru.qa.config.constants.AuthConfig;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static ru.qa.specs.ApiSpecs.*;

public class RestfulBookerAuthApi {
    private RestfulBookerAuthApi() {
        throw new AssertionError("Utility class");
    }

    public static Response authResponseRestulBooker(String userName, String password) {
        Response response =  given()
                .spec(restfulBookRequestSpec())
                .body(Map.of("username", userName, "password", password)).contentType(ContentType.JSON)
                .log().all()
                .when()
                .post(AuthConfig.RESFTFUL_BOOKER_AUTH)
                .then()
                .log().all()
                .extract().response();

        Assertions.assertNotNull(response);
        return response;
    }
}
