package ru.qa.day28;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import ru.qa.specs.ApiSpecs;

import static io.restassured.RestAssured.given;

public class AllureApiTest {

    @Test
    @Epic("API testing")
    @Feature("Allure inbuild logging")
    @Story("Test inbuild logins allure report via filter in SPEC")
    void shouldLogRequestToAllure(){
        given()
                .spec(ApiSpecs.baseRequestSpec())
                .when()
                .get("/users")
                .then()
                .statusCode(200);
    }
}
