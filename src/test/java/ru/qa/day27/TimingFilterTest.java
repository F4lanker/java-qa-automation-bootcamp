package ru.qa.day27;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.qa.filter.TimingFilter;

import static io.restassured.RestAssured.given;
import static ru.qa.specs.ApiSpecs.*;

public class TimingFilterTest {

    @Test
    @DisplayName("GET: /users/[id] should pass within threshold")
    void shouldPassWithinThreshold(){
        int responseThreshold = 3000; // here I want to make int private, but highlighted by IJ
        int userId = 1;

        given()
                .spec(baseRequestSpec())
                .filter(new TimingFilter(responseThreshold))
                .log().all()
                .when()
                .get("/users/" + userId)
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Should fail with extra low threshold")
    void shouldFailWhenExceedsThreshold(){
        int responseThreshold = 1;
        int userId = 1;

        given()
            .spec(baseRequestSpec())
            .filter(new TimingFilter(responseThreshold))
            .log().all()
            .when()
            .get("/users/" + userId)
            .then()
    }
}
