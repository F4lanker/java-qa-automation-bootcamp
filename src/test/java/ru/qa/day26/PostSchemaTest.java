package ru.qa.day26;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.qa.specs.ApiSpecs;

import static ru.qa.util.SchemaValidatorUtil.checkSchemaJson;

public class PostSchemaTest {

    @Test
    @DisplayName("GET: /posts/[id] should match post-schema.json")
    void shouldMatchPostSchema() {
        int postId = 1;
        String getPath = "/posts/" + postId;
        String schemaPath = "schemas/post-schema.json";
        checkSchemaJson(getPath, schemaPath, ApiSpecs.baseRequestSpec());
    }

    @Test
    @DisplayName("GET: /posts should match post-list-schema.json")
    void shouldMatchPostListSchema() {
        String getPath = "/posts";
        String schemaPath = "schemas/post-list-schema.json";
        checkSchemaJson(getPath, schemaPath, ApiSpecs.baseRequestSpec());
    }
}
