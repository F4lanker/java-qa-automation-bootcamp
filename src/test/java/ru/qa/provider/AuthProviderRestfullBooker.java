package ru.qa.provider;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import ru.qa.config.constants.AuthConfig;
import ru.qa.specs.ApiSpecs;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static ru.qa.config.constants.ApiConfig.RESTFULBKR_URL;
import static ru.qa.specs.ApiSpecs.*;

public class AuthProviderRestfullBooker {

    protected static String accessToken;
    protected static RequestSpecification authSpec;
    public static String userName = "admin";
    protected static String password = "password123";

    @BeforeAll
    static void authenticate() throws JsonProcessingException {
        accessToken = given()
                .spec(rstflBookerReqSpec())
                .header("Content-Type", "application/json")
                .body((Map.of("username", userName, "password", password))).contentType(ContentType.JSON)
                .log().all()
                .when()
                .post(AuthConfig.RESFULL_BOOKER_AUTH)
                .then()
                .log().all()
                .extract().cookie("token");

        Assertions.assertNotNull(accessToken); //check the token exists

        authSpec = ApiSpecs.authRequestSpecCookie(RESTFULBKR_URL ,accessToken);


    }
}
