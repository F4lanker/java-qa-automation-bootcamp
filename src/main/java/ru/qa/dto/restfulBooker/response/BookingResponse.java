package ru.qa.dto.restfulBooker.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data

public class BookingResponse {

	@JsonProperty("booking")
	private BookingDetailsResponse booking;

	@JsonProperty("bookingid")
	private int bookingid;

}