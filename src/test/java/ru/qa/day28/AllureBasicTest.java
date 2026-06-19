package ru.qa.day28;

import io.qameta.allure.*;
import io.qameta.allure.restassured.AllureRestAssured;
import org.junit.jupiter.api.Test;
import ru.qa.util.SchemaValidatorUtil;

import static io.restassured.RestAssured.given;
import static ru.qa.specs.ApiSpecs.*;

import io.qameta.allure.Epic;

@Epic("API testing")

public class AllureBasicTest {
    @Test
    @Feature("User management")
    @Story("Get user by id")
    @Description("GET/user/[id] should response with valid user data")
    @Severity(SeverityLevel.CRITICAL)
    void shouldGetUserWithAllureSteps() {

        int userId = 1;
        String schemaPath = "schemas/user-schema.json";

        String response = step1_sendGetUserRequest(userId);
        step2_verifySchema(response, schemaPath);
    }

    @Step("Send GET /users/[id]")
    private String step1_sendGetUserRequest(int userId) {
        return given()
                .filter(new AllureRestAssured())
                .spec(baseRequestSpec())
                .when()
                .get("/users/" + userId)
                .then()
                .statusCode(200)
                .extract().body().asString();
    }

    @Step("Verify user JSON schema via util method")
    private void step2_verifySchema(String response, String schemaPath) {
        SchemaValidatorUtil.checkSavedJsonSchema(response, schemaPath);
    }
}
}
