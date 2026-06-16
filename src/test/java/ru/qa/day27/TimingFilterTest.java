package ru.qa.day27;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.qa.filter.TimingFilter;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.qa.specs.ApiSpecs.*;

public class TimingFilterTest {

    private static final int PASS_THRESHOLD_MS = 3000;
    private static final int FAIL_THRESHOLD_MS = 1;
    private static final String TEST_PATH = "/users/1";

    @Test
    @DisplayName("GET: /users/[id] should pass within threshold")
    void shouldPassWithinThreshold() {
        given()
                .spec(baseRequestSpec())
                .filter(new TimingFilter(PASS_THRESHOLD_MS))
                .when()
                .get(TEST_PATH)
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Should fail with extra low threshold")
    void shouldFailWhenExceedsThreshold() {

        assertThrows(AssertionError.class, () ->
                             given()
                                     .spec(baseRequestSpec())
                                     .filter(new TimingFilter(FAIL_THRESHOLD_MS))
                                     .when()
                                     .get(TEST_PATH)
                                     .then()
                                     .statusCode(200)
                    );
    }
}
