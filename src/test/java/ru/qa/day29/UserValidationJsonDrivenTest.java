package ru.qa.day29;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import testData.UserTestCase;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static ru.qa.specs.ApiSpecs.*;

public class UserValidationJsonDrivenTest {

    static Stream<UserTestCase> userTestCases() throws IOException {

        InputStream is = UserValidationJsonDrivenTest.class
                .getClassLoader()
                .getResourceAsStream("testData/UserTestCase/users.json");

        ObjectMapper mapper = new ObjectMapper();

        UserTestCase[] cases = mapper.readValue(is, UserTestCase[].class);
        return Arrays.stream(cases);
    }

    @ParameterizedTest(name = "User validation: {0}")
    @MethodSource("userTestCases")
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
