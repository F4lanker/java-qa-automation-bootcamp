package ru.qa.day29;


import io.qameta.allure.Allure;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import ru.qa.provider.JsonFileArgumentsProvider.JsonSource;
import ru.qa.testData.UserTestCase;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static ru.qa.specs.ApiSpecs.*;

public class UserValidationJsonDrivenTest {

    @Feature("User API")
    @Story("Validate user")
    @DisplayName("Validate user")
    @ParameterizedTest(name = " > {0}")
    @JsonSource(
            path = "testData/UserTestCase/users.json",
            type = UserTestCase.class
    )
    void shouldValidateUserCreation(UserTestCase testCase) {
        Allure.step("POST /users → expect " + testCase.getExpectedStatus());
        given()
                .spec(baseRequestSpec())
                .body(Map.of("name", testCase.getName(), "email", testCase.getEmail()))
                .when()
                .post("/users")
                .then()
                .statusCode(testCase.getExpectedStatus()); //fake API responses 201 even again empty name or invalid email, so all responses are success
    }
}
