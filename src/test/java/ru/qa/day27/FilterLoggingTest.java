package ru.qa.day27;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.qa.filter.RequestLoggingFilter;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.qa.specs.ApiSpecs.*;

public class FilterLoggingTest {

    // JUnit 5 позволяет захватить System.out
// Добавь в класс:
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outputCapture = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() { System.setOut(new PrintStream(outputCapture));
        }
        
    @AfterEach
    void tearDown() { System.setOut(originalOut); }


    @Test
    @DisplayName("GET /users/[id] has a custom logs")
            void shouldLogRequestAndResponse() {
        given()
                .spec(baseRequestSpec())
                .filter(new RequestLoggingFilter())
                .when()
                .get("/users/1")
                .then()
                .statusCode(200);

        String log = outputCapture.toString();
        assertTrue(log.contains("GET"));
        assertTrue(log.contains("200"));
        System.out.println(log); // почему не видно в консоли этот вывод?
    }


}
