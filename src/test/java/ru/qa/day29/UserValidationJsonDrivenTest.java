package ru.qa.day29;


import org.junit.jupiter.params.ParameterizedTest;
import ru.qa.provider.JsonFileArgumentsProvider.JsonSource;
import testData.UserTestCase;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static ru.qa.specs.ApiSpecs.*;

public class UserValidationJsonDrivenTest {

    @ParameterizedTest(name = "User validation: {0}")
    @JsonSource(
            path = "testData/UserTestCase/users.json",
            type = UserTestCase.class
    )
    void shouldValidateUserCreation(UserTestCase testCase) {
        given()
                .spec(baseRequestSpec())
                .body(Map.of("name", testCase.getName(), "email", testCase.getEmail()))
                .when()
                .post("/users")
                .then()
                .statusCode(testCase.getExpectedStatus()); //fake API responses 201 even again empty name or invalid email, so all responses are success
    }
}
