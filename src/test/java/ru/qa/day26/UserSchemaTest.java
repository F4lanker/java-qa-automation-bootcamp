package ru.qa.day26;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static ru.qa.specs.ApiSpecs.*;
import static ru.qa.util.SchemaValidatorUtil.checkSchemaJson;

public class UserSchemaTest {

    @Test
    @DisplayName("GET: /users/[id] should match user-schema.json")
    void shouldMatchUserSchema() {
        int userId = 1;
        String getPath = "/users/" + userId;
        String schemaPath = "schemas/user-schema.json";
        checkSchemaJson(getPath, schemaPath, baseRequestSpec());
    }

    @Test
    @DisplayName("GET: /users should match user-list-schema.json")
    void shouldMatchUserListSchema() {
        String getPath = "/users";
        String schemaPath = "schemas/user-list-schema.json";
        checkSchemaJson(getPath, schemaPath, baseRequestSpec());
    }
}
