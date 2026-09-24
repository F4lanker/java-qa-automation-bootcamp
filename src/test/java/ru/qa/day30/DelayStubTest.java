package ru.qa.day30;

import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.qa.base.WireMockExt;
import ru.qa.filter.TimingFilter;
import ru.qa.specs.ApiSpecs;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;

public class DelayStubTest extends WireMockExt {

    private static final int DELAY = 300;
    private static final int PASS_THRESHOLD_MS = 800;
    private static final int FAIL_THRESHOLD_MS = 200;

    @Step
    void createStub() {
        wm.stubFor(get(urlEqualTo("/users"))
                           .willReturn(aResponse()
                                               .withStatus(200)
                                               .withHeader("Content-Type", "application/json")
                                               .withBody("Sample stub")
                                               .withFixedDelay(DelayStubTest.DELAY)
                                      ));
    }

    @Feature("Network condition and delay")
    @Story("Delay")
    @DisplayName("False positive test: response within timeout")
    @Test
    void delayPassUserTest() throws InterruptedException {
        createStub();
        given()
                .spec(ApiSpecs.baseRequestSpec(wm.baseUrl()))
                .filter(new TimingFilter(PASS_THRESHOLD_MS))
                .log().all()
                .when().get("/users")
                .then()
                .statusCode(200);
    }

    @Feature("Network condition and delay")
    @Story("Delay")
    @DisplayName("Timeout Exceeds")
    @Test
    void delayFailUserTest() throws AssertionError {
        createStub();
        Assertions.assertThrows(AssertionError.class, () -> {
            given()
            .spec(ApiSpecs.baseRequestSpec(wm.baseUrl()))
            .filter(new TimingFilter(FAIL_THRESHOLD_MS))
                    .log().all()
            .when().get("/users")
            .then()
            .statusCode(200);
        });
    }

}
