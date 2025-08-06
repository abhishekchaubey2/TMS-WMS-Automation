
package com.delhivery.Express.pojo.WrhStatus.Request;

import java.util.LinkedHashMap;

import java.util.Map;

import com.delhivery.Express.pojo.AgWt.Request.MaxWt;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)

public class StatusRequest {

	@JsonProperty("phone")
	private String phone;
	@JsonProperty("city")
	private String city;
	@JsonProperty("name")
	private String name;
	@JsonProperty("pin")
	private String pin;
	@JsonProperty("address")
	private String address;
	@JsonProperty("country")
	private String country;
	@JsonProperty("contact_person")
	private String contactPerson;
	@JsonProperty("email")
	private String email;
	@JsonProperty("registered_name")
	private String registeredName;
	@JsonProperty("return_address")
	private String returnAddress;
	@JsonProperty("return_pin")
	private String returnPin;
	@JsonProperty("return_city")
	private String returnCity;
	@JsonProperty("return_state")
	private String returnState;
	@JsonProperty("return_country")
	private String returnCountry;

}
