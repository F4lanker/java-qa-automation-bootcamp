package ru.qa.dto.restfulBooker.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Booking{

	@JsonProperty("firstname")
	private String firstname;

	@JsonProperty("additionalneeds")
	private String additionalneeds;

	@JsonProperty("bookingdates")
	private Bookingdates bookingdates;

	@JsonProperty("totalprice")
	private int totalprice;

	@JsonProperty("depositpaid")
	private boolean depositpaid;

	@JsonProperty("lastname")
	private String lastname;

	public String getFirstname(){
		return firstname;
	}

	public String getAdditionalneeds(){
		return additionalneeds;
	}

	public Bookingdates getBookingdates(){
		return bookingdates;
	}

	public int getTotalprice(){
		return totalprice;
	}

	public boolean isDepositpaid(){
		return depositpaid;
	}

	public String getLastname(){
		return lastname;
	}
}