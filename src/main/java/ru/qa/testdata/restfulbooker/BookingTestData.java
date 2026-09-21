package ru.qa.testdata.restfulbooker;

import ru.qa.dto.restfulBooker.request.BookingRequest;
import ru.qa.dto.restfulBooker.request.BookingdatesRequest;

public class BookingTestData {
    private BookingTestData() { throw new AssertionError("Utility class"); }

    public static BookingRequest.BookingRequestBuilder valid() {
        return BookingRequest.builder()
                             .firstname("Test")
                             .lastname("User")
                             .totalprice(123)
                             .depositpaid(true)
                             .additionalneeds("Extra bed")
                             .bookingdates(new BookingdatesRequest("2026-01-01", "2027-02-02"));
    }

    public static BookingRequest.BookingRequestBuilder updated() {
        return BookingRequest.builder()
                .firstname("UpdateName")
                .lastname("UpdateLastName")
                .totalprice(888)
                .depositpaid(false)
                .additionalneeds("Nothing")
                .bookingdates(new BookingdatesRequest("2026-02-02", "2027-01-01"));
    }
}
