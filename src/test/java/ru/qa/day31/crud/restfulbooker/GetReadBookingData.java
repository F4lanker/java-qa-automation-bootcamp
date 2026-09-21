package ru.qa.day31.crud.restfulbooker;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.qa.api.restfulbooker.RestfulBookerBookingApi;
import ru.qa.dto.restfulBooker.request.BookingRequest;
import ru.qa.dto.restfulBooker.response.BookingDetailsResponse;
import ru.qa.dto.restfulBooker.response.BookingResponse;
import ru.qa.testdata.restfulbooker.BookingTestData;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static ru.qa.specs.ApiSpecs.*;
import static org.assertj.core.api.Assertions.assertThat;

public class GetReadBookingData {

    @Test
    @DisplayName("GET:/booking +[id]")
    @Epic("Restful-booker")
    @Feature("Day 31  - Mini project API tests")
    @Story("requested booking data is equal with POST data")
    @Severity(SeverityLevel.CRITICAL)
    void getReadBookingData() {
        BookingRequest initialRequest = BookingTestData.valid().build();
        Response initialResponse = RestfulBookerBookingApi.bookingApi(initialRequest);
        int bookingId = initialResponse.as(BookingResponse.class).getBookingid();

        Response response = given()
                .spec(restfulBookRequestSpec())
                .when()
                .get("/booking/" + bookingId)
                .then()
                .log().all()
                .extract().response();
        BookingDetailsResponse bookingDetailsResponse = response.as(BookingDetailsResponse.class);

        assertNotNull(response);
        assertThat(bookingDetailsResponse).usingRecursiveComparison()
                                          .isEqualTo(initialRequest); // check the response data is equal to requested initially
    }
}
