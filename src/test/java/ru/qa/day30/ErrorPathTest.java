package ru.qa.day30;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import ru.qa.base.WireMockExt;
import ru.qa.days.util.JsonUtils;
import ru.qa.provider.JsonFileArgumentsProvider;
import ru.qa.testData.UserErrorTestCase;

import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static ru.qa.specs.ApiSpecs.*;

public class ErrorPathTest extends WireMockExt {

    @Step("Create WM stub")
    void createErrStub(UserErrorTestCase testCase) throws JsonProcessingException {
        wm.stubFor(get(urlEqualTo("/users/" + testCase.getId()))
                           .willReturn(aResponse()
                                               .withStatus(testCase.getExpectedStatus())
                                               .withHeader("Content-Type", "application/json")
                                               .withBody(JsonUtils.toJson(Map
                                                                                  .of("error_message", testCase.getError_message())))
                                      )
                  );

    }

    @Step
    void checkStubBody(UserErrorTestCase testCase)  {
        given()
                .spec(baseRequestSpec(wm.baseUrl()))
                .when()
                .get("users/" + testCase.getId())
                .then()
                .log().all()
                .statusCode(testCase.getExpectedStatus())
                .body("error_message", equalTo(testCase.getError_message()));
    }

    @Feature("User API")
    @Story("Invalid user")
    @DisplayName("Invalid user")
    @ParameterizedTest(name=">{1}")
    @JsonFileArgumentsProvider.JsonSource(
            path = "testData/UserTestCase/userErrorStub.json",
            type = UserErrorTestCase.class
    )
    void errorPath(UserErrorTestCase testCase) throws JsonProcessingException {
        createErrStub(testCase);
        checkStubBody(testCase);
    }

}
