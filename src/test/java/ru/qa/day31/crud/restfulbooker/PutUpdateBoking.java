package ru.qa.day31.crud.restfulbooker;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import ru.qa.api.restfulbooker.RestfulBookerBookingApi;
import ru.qa.base.AuthProviderRestfulBooker;
import ru.qa.dto.restfulBooker.request.BookingRequest;
import ru.qa.dto.restfulBooker.response.BookingDetailsResponse;
import ru.qa.dto.restfulBooker.response.BookingResponse;
import ru.qa.testdata.restfulbooker.BookingTestData;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.*;
import static ru.qa.specs.ApiSpecs.*;

public class PutUpdateBoking extends AuthProviderRestfulBooker {
    @Test
    void updateBookingTest() {

        BookingRequest request = BookingTestData.valid().build();
        BookingRequest updateUserRequest = BookingTestData.updated().build();

        // 1. Create initial booking
        Response response = RestfulBookerBookingApi.bookingApi(request);

        // Get ID
        int bookingId = response.as(BookingResponse.class).getBookingid();

        // 2. Update PUT request with auth Spec
        Response updatedBookingResponse = given()
                .spec(restfulBookRequestSpec())
                .spec(authSpec) // auth swith inhereted method Auth spec
                .body(updateUserRequest)
                .when()
                .put("/booking/" + bookingId)
                .then()
                .statusCode(200)
                .extract().response();

        // 3.  Map response after PUT updating to BookingDetailsResponse
        BookingDetailsResponse bookingResponse = updatedBookingResponse.as(BookingDetailsResponse.class);

        // 4. Compare (bookingResponse) with updated data (updateUserRequest)
        assertThat(bookingResponse)
                .usingRecursiveComparison()
                .isEqualTo(updateUserRequest);
    }
}
