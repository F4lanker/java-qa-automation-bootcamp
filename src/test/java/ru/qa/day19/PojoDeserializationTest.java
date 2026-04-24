package ru.qa.day19;

import io.restassured.common.mapper.TypeRef;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.qa.base.BaseApiTest;
import ru.qa.dto.PostDto;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class PojoDeserializationTest extends BaseApiTest {

    @DisplayName("GET /posts/[id] - title is NOT null and id is according to requested")
    @Test
    public void shouldDeserializePostToPojo() {
        int id = 1;

        PostDto posts = given()
                .spec(requestSpec)
                .when()
                .get(getBasePath() + "/" + id)
                .then()
                .extract().as(PostDto.class);

        assertEquals(id, posts.getId());
        assertNotNull(posts.getTitle());
    }

    @DisplayName("GET /posts?_limit=[limit] - list size is X, and all element is samples of PostDto.class")
    @Test
    void shouldDeserializeListOfPosts() {
        int limit = 5;
        List<PostDto> posts = given()
                .spec(requestSpec)
                .queryParam("_limit", limit)
                .when()
                .get(getBasePath())
                .then()
                .extract().as(new TypeRef<List<PostDto>>() {
                });

        assertEquals(limit, posts.size());
        assertTrue(posts.stream()
                        .allMatch(x -> x.getClass().equals(PostDto.class)), "All element is samples of PostDto.class");
    }

    @Test
    @DisplayName("GET /posts/[id] - validates all fields via deserialization")
    public void shouldValidateAllFields() {
        int id = 1;
PostDto posts = given()
        .spec(requestSpec)
        .when()
        .get(getBasePath() + "/" + id)
        .then()
        .extract().as(PostDto.class);

assertAll(
        ()-> assertNotNull(posts.getId()),
        ()->assertNotNull(posts.getUserId()),
        ()->assertFalse(posts.getTitle().isEmpty()),
        ()->assertFalse(posts.getBody().isEmpty())
         );
    }

    @Override
    protected String getBasePath() {
        return "/posts";
    }
}
