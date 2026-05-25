package ru.qa.day25;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.qa.specs.ApiSpecs;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class FileUploadTest {

    @Test
    @DisplayName("Should upload .txt file from resource and response body is NOT null")
    void shouldUploadFileSuccessfully(){
        given()
                .spec(ApiSpecs.httpBinRequestSpec())
                .contentType("multipart/form-data")
                .multiPart("file", new File("src/test/resources/test-file.txt"))
                .when()
                .log().all()
                .post("/post")
                .then()
                .statusCode(200)
                .body("files.file", notNullValue());
    }

    @Test
    @DisplayName("Should upload meta-data with .txt file")
    void shouldUploadFileetaData(){
        given()
                .spec(ApiSpecs.httpBinRequestSpec())
                .contentType("multipart/form-data")
                .multiPart(new File(ClassLoader.getSystemResource("test-file.txt").getFile()))
                .multiPart("description", "test upload")
                .log().all()
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("form.description", equalTo("test upload"));
    }
}
