package ru.qa.day19;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.qa.base.BaseApiTest;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PathParamsTest extends BaseApiTest {

   @DisplayName("GET: responses user ID requested in path param")
   @Test
   void shouldGetPostById(){
       int id = 1;

       given()
               .spec(requestSpec)
               .pathParams("id", id)
               .when()
               .get("/posts/{id}")
               .then()
               .body("id", equalTo(id));
   }

@DisplayName("GET: path param UserId responses user name")
@Test
void shouldGetUserById(){
       int userId = 1;
       given()
       .spec(requestSpec)
       .pathParams("userId", userId)
       .when()
       .get("/users/{userId}")
       .then()
       .body("name", notNullValue());
}

@DisplayName("GET: all posts have requested in path param userId")
@Test
void shouldGetUserPosts(){
       int userId = 1;
       List<Integer> list = given()
       .spec(requestSpec)
       .pathParams("userId", userId)
       .when()
       .get("/users/{userId}/posts")
       .then()
               .extract().jsonPath().getList("userId", Integer.class);

       assertTrue(list.stream().allMatch(id -> id == userId), "All posts should have expected ID");
}

@Override
    protected String getBasePath() {
        return "/posts";
    }
}
