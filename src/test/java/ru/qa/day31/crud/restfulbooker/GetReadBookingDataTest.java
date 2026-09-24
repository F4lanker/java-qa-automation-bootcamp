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

import static org.assertj.core.api.Assertions.*;

public class GetReadBookingData {

    @Test
    @DisplayName("GET:/booking +[id]")
    @Epic("Restful-booker")
    @Feature("Day 31  - Mini project API tests")
    @Story("requested booking data is equal with POST data")
    @Severity(SeverityLevel.CRITICAL)
    void getReadBookingData() {
        //Arrange: build initial request for POST method
        BookingRequest initialRequest = BookingTestData.valid().build();
        Response initialResponse = RestfulBookerBookingApi.createBooking(initialRequest);
        int bookingId = initialResponse.as(BookingResponse.class).getBookingid();

        // Act:  Call GET method
        Response response = RestfulBookerBookingApi.getBooking(bookingId);
        BookingDetailsResponse bookingDetailsResponse = response.as(BookingDetailsResponse.class);

        //Assert: Checking initial request is equal GET responded
        response.then().statusCode(200);
        assertThat(bookingDetailsResponse).usingRecursiveComparison()
                                          .isEqualTo(initialRequest);
    }
}
