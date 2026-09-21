package ru.qa.dto.restfulBooker.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingDetailsRequest {

	@JsonProperty("firstname")
	private String firstname;

	@JsonProperty("additionalneeds")
	private String additionalneeds;

	@JsonProperty("bookingdates")
	private BookingdatesRequest bookingdatesRequest;

	@JsonProperty("totalprice")
	private int totalprice;

	@JsonProperty("depositpaid")
	private boolean depositpaid;

	@JsonProperty("lastname")
	private String lastname;

}