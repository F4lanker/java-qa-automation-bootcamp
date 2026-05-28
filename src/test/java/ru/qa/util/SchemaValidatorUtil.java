package ru.qa.util;

import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public final class SchemaValidatorUtil {
    private SchemaValidatorUtil(){
        throw new AssertionError("Utility class");
    }
    public static void checkSchemaJson (String getPath, String jsonSchemaPath, RequestSpecification spec){
        given()
                .spec(spec)
                .when()
                .get(getPath)
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath(jsonSchemaPath));
    }
}
