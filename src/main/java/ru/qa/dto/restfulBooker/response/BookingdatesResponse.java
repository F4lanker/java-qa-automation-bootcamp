package ru.qa.dto.restfulBooker.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Bookingdates{

	@JsonProperty("checkin")
	private String checkin;

	@JsonProperty("checkout")
	private String checkout;

	public String getCheckin(){
		return checkin;
	}

	public String getCheckout(){
		return checkout;
	}
}