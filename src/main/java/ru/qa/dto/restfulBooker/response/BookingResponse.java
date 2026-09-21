package ru.qa.dto.restfulBooker.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class BookingIdResponse {

	@JsonProperty("booking")
	private BookingDetailsResponse booking;

	@JsonProperty("bookingid")
	private int bookingid;

	public BookingDetailsResponse getBooking(){
		return booking;
	}

	public int getBookingid(){
		return bookingid;
	}
}