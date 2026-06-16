package ru.qa.day27;

import listener.RetryListener;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.qa.filter.CustomRequestLoggingFilter;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.qa.specs.ApiSpecs.*;

@ExtendWith(RetryListener.class)

public class FilterLoggingTest {

    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outputCapture = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputCapture));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        outputCapture.reset();
    }

    @AfterAll
    public static void saveFailed() { // save failed test to resources.failedTests.txt
        RetryListener.saveFailedTests();
    }


    @Test
    @DisplayName("GET /users/[id] has a custom logs")
    void shouldLogRequestAndResponse() {
        given()
                .spec(baseRequestSpec())
                .filter(new CustomRequestLoggingFilter())
                .when()
                .get("/users/1")
                .then()
                .statusCode(200);

        String log = outputCapture.toString();
        assertTrue(log.contains("GET"));
        assertTrue(log.contains("200"));

        originalOut.println(log);
    }

    @Test
    @DisplayName("GET /posts/1 - logs saved to the api-requests.log")
    void shouldWriteLogsToFile() throws Exception {
        //create FileStream instead of System.out
        Path logFile = Paths.get("target", "logs", "api-requests.log");
        Files.createDirectories(logFile.getParent());

        try (PrintStream fileStream = new PrintStream(logFile.toFile())) {
            given()
                    .spec(baseRequestSpec())
                    .filter(new CustomRequestLoggingFilter(fileStream))
                    .when()
                    .get("/posts/1")
                    .then()
                    .statusCode(200);
        }

        assertTrue(Files.size(logFile) > 0);
    }

}
