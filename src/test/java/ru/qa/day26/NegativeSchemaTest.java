package ru.qa.day26;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.qa.specs.ApiSpecs.*;
import static ru.qa.util.SchemaValidatorUtil.checkSchemaJson;

public class NegativeSchemaTest {
    @Test
    @DisplayName("GET: /post should fail with error if non existen field in schema is required")
    void shouldFailWhenRequiredFieldMissing(){
        String getPath = "/posts";
        String schemaPath = "schemas/invalid-schema.json";

        assertThrows(AssertionError.class, () -> {
            checkSchemaJson(getPath, schemaPath, baseRequestSpec());
        });
    }
}
