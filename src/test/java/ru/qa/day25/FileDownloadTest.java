package ru.qa.day25;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.qa.specs.ApiSpecs.*;

public class FileDownloadTest {

    @Test
    @DisplayName("GET, check download image successfully and byte length>0")
    void shouldDownloadImageSuccessfully() {
        byte[] imageBytes = given()
                .spec(httpBinRequestSpec())
                .header("Accept", "image/png")
                .when()
                .get("/image/png")
                .then()
                .statusCode(200)
                .extract().body().asByteArray();

        assertTrue(imageBytes.length > 0);
    }

    @Test
    @DisplayName("GET. Download file to the disc")
    void shouldDownloadFileToDisc() throws IOException {
        Path filePath = Paths.get("target", "downloads", "downloaded-image.png"); // path = target/downloads/downloaded-image.png, here we avoid hardcode on different OS-s
        Files.createDirectories(filePath.getParent()); // target/downloads/

        byte[] imageBytes = given()
                .spec(httpBinRequestSpec())
                .header("Accept", "image/png")
                .when()
                .get("/image/png")
                .then()
                .statusCode(200)
                .extract().body().asByteArray();

        Files.write(filePath, imageBytes);
        assertTrue(Files.exists(filePath));
        assertTrue(Files.size(filePath) > 0 );
    }

}
