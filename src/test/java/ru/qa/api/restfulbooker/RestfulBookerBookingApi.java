package ru.qa.api.restfulbooker;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import ru.qa.dto.restfulBooker.request.BookingRequest;

import static io.restassured.RestAssured.given;
import static ru.qa.specs.ApiSpecs.*;

public class RestfulBookerBookingApi {
    private RestfulBookerBookingApi() {throw new AssertionError("Utility class");
    }
    public static Response createBooking(BookingRequest request) {



        return given()
                .spec(restfulBookRequestSpec())
                .body(request)
                .contentType(ContentType.JSON)
                .when()
                .post("/booking")
                .then()
                .log().all()
                .extract().response();
    }
}
