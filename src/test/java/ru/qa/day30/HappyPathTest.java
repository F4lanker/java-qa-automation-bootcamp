package ru.qa.day30;


import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import ru.qa.base.WireMockExt;
import ru.qa.days.util.JsonUtils;
import ru.qa.provider.JsonFileArgumentsProvider.JsonSource;
import ru.qa.testData.UserTestCase;

import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static ru.qa.specs.ApiSpecs.*;


public class HappyPathTest extends WireMockExt {


    @Step("Create mock server stub")
    void createWMStub(UserTestCase testCase) throws JsonProcessingException {
        wm.stubFor(get(urlEqualTo("/users/" + testCase.getId()))
                           .willReturn(aResponse()
                                               .withStatus(testCase.getExpectedStatus())
                                               .withHeader("Content-Type", "application/json")
                                               .withBody(JsonUtils.toJson(Map
                                                                 .of("name", testCase.getName(), "email", testCase.getEmail()
                                                                    )))));
    }


    @Step("Check stub status and body")
    void checkStubBody(UserTestCase testCase) {
        given()
                .spec(baseRequestSpec(wm.baseUrl()))
                .when()
                .get("users/" + testCase.getId())
                .then()
                .statusCode(testCase.getExpectedStatus())
                .body("name", equalTo(testCase.getName()));
    }

    @Feature("User API")
    @Story("Validate user")
    @DisplayName("Validate user")
    @ParameterizedTest(name = ">{0}")
    @JsonSource(
            path = "testData/UserTestCase/userStub.json",
            type = UserTestCase.class
    )
    void happyPath(UserTestCase testCase) throws JsonProcessingException {

        createWMStub(testCase);
        checkStubBody(testCase);

    }
}
