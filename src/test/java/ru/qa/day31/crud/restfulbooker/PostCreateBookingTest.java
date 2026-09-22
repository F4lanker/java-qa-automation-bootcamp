package ru.qa.day31.crud.restfulbooker;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.qa.api.restfulbooker.RestfulBookerBookingApi;
import ru.qa.dto.restfulBooker.request.BookingRequest;
import ru.qa.dto.restfulBooker.response.BookingResponse;
import ru.qa.testdata.restfulbooker.BookingTestData;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PostCreateBookingTest {

    @Test
    @DisplayName("POST:/booking -valid")
    @Epic("Restful-booker")
    @Feature("Day 31  - Mini project API tests")
    @Story("Positive case filling all required fields")
    @Severity(SeverityLevel.CRITICAL)
    void createBookingTest() {

        BookingRequest request = BookingTestData.valid().build();
        Response response = RestfulBookerBookingApi.createBooking(request);

        response.then().statusCode(200);

        BookingResponse bookingResponse = response.as(BookingResponse.class);
        assertNotNull(bookingResponse);
        assertTrue(bookingResponse.getBookingid() > 0, "Booking Id should be greater than 0");
        assertThat(bookingResponse.getBooking())
                .usingRecursiveComparison()
                .isEqualTo(request); //compare request and response ingoring class difference according to data inside
    }
}
