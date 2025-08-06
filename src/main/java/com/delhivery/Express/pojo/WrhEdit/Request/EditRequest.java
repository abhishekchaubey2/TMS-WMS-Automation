
package com.delhivery.Express.pojo.WrhEdit.Request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import groovyjarjarantlr.collections.List;
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

public class EditRequest {

	@JsonProperty("phone")
	public String phone;
	@JsonProperty("city")
	public String city;
	@JsonProperty("name")
	public String name;
	@JsonProperty("pin")
	public String pin;
	@JsonProperty("address")
	public String address;
	@JsonProperty("country")
	public String country;
	@JsonProperty("email")
	public String email;
	@JsonProperty("registered_name")
	public String registeredName;
	@JsonProperty("return_address")
	public String returnAddress;
	@JsonProperty("return_pin")
	public String returnPin;
	@JsonProperty("return_city")
	public String returnCity;
	@JsonProperty("return_state")
	public String returnState;
	@JsonProperty("return_country")
	public String returnCountry;

}
