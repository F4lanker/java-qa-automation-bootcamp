package ru.qa.client;

import ru.qa.dto.UserDto;

import static io.restassured.RestAssured.given;

public class UserApiClient extends BaseApiClient {

    @Override
    protected String getEndpoint() {
        return "/users";
    }

    @Override
    protected <T> T sendGetRequest(int id, Class<T> responseClass) {
        return given().spec(spec)
                      .when().get("/" + id)
                      .then().statusCode(200)
                      .extract().as(responseClass);
    }

    public UserDto getUserById(int id) {
        return getById(id, UserDto.class);
    }
}