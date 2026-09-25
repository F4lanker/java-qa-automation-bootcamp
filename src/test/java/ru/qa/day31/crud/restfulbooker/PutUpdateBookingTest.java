package ru.qa.day31.crud.restfulbooker;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.qa.api.restfulbooker.RestfulBookerBookingApi;
import ru.qa.base.AuthProviderRestfulBooker;
import ru.qa.dto.restfulBooker.request.BookingRequest;
import ru.qa.dto.restfulBooker.response.BookingDetailsResponse;
import ru.qa.dto.restfulBooker.response.BookingResponse;
import ru.qa.testdata.restfulbooker.BookingTestData;

import static org.assertj.core.api.Assertions.*;

public class PutUpdateBookingTest extends AuthProviderRestfulBooker {
    @Test
    @DisplayName("PUT:/booking -valid")
    @Epic("Restful-booker")
    @Feature("Day 31  - Mini project API tests")
    @Story("Positive case updating existed booking")
    @Severity(SeverityLevel.NORMAL)
    void updateBookingTest() {
        // Arrange: existing booking to update  
        BookingRequest request = BookingTestData.valid().build();
        BookingRequest updateRequest = BookingTestData.updated().build();
        Response createResponse = RestfulBookerBookingApi.createBooking(request);
        int bookingId = createResponse.as(BookingResponse.class).getBookingid();

        // Act: Update PUT request with auth Spec calling updateBooking method
        Response updateResponse = RestfulBookerBookingApi
                .updateBooking(updateRequest, authSpec, bookingId);
        BookingDetailsResponse updatedResponse = updateResponse.as(BookingDetailsResponse.class);



        // Assert: Compare (bookingResponse) with updated data (updateUserRequest)
        updateResponse.then().statusCode(200);
        assertThat(updatedResponse)
                .usingRecursiveComparison()
                .isEqualTo(updateRequest);
    }
}
