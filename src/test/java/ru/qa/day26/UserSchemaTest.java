package ru.qa.day26;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static ru.qa.specs.ApiSpecs.*;

public class UserSchemaTest {

    private void checkSchemaJson (String getPath, String jsonSchemaPath){
        given()
                .spec(baseRequestSpec())
                .when()
                .get(getPath)
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath(jsonSchemaPath));
    }

    @Test
    @DisplayName("GET: /users/[id] should match user-schema.json")
    void shouldMatchUserSchema() {
        int userId = 1;
        String getPath = "/users/" + userId;
        String schemaPath = "schemas/user-schema.json";
       checkSchemaJson(getPath, schemaPath);
    }

    @Test
    @DisplayName("GET: /users should match user-list-schema.json")
    void shouldMatchUserListSchema() {
        String getPath = "/users";
        String schemaPath = "schemas/user-list-schema.json";
        checkSchemaJson(getPath, schemaPath);
    }
}
