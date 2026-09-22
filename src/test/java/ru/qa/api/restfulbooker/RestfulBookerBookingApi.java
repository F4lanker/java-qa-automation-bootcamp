package ru.qa.api.restfulbooker;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import ru.qa.dto.restfulBooker.request.BookingRequest;

import static io.restassured.RestAssured.given;
import static ru.qa.specs.ApiSpecs.*;

public class RestfulBookerBookingApi {
    private RestfulBookerBookingApi() {
        throw new AssertionError("Utility class");
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

    public static Response updateBooking(BookingRequest request, RequestSpecification authSpec, int bookingId) {
        return given()
                .spec(authSpec) // auth with inherited method Auth spec
                .body(request)
                .when()
                .put("/booking/" + bookingId)
                .then()
                .extract().response();
    }

    public static Response getBooking(BookingRequest request, int bookingId) {

        return given()
                .spec(restfulBookRequestSpec())
                .when()
                .get("/booking/" + bookingId)
                .then()
                .log().all()
                .extract().response();

    }
}
