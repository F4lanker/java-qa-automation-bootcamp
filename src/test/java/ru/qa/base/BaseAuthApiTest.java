package ru.qa.base;

import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.given;

public class BaseAuthApiTest {
    protected static String accessToken;
    protected static RequestSpecification authSpec;



    @BeforeAll
    static void authenticate(RequestSpecification spec, String username, String password) {
        accessToken = given()
                .spec(spec)
                .auth().basic(username, password)
                .when()
                .get("basic-auth/user/passwd")
                .then()
                .extract().jsonPath().getString("accessToken");
        System.out.println("accessToken: " + accessToken);
        // 1. Получи токен через POST /api/login
        // 2. Сохрани в accessToken
        // 3. Создай authSpec с Bearer токеном
    }
}
}