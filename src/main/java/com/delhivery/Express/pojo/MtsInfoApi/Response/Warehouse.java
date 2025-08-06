package com.delhivery.Express.pojo.MtsInfoApi.Response;

import java.io.Serializable;

import com.delhivery.Express.pojo.AgWt.Request.MaxWt;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonPropertyOrder({ "latitude", "city", "state", "country_code", "longitude" })
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Warehouse implements Serializable {

	@JsonProperty("latitude")
	public Double latitude;
	@JsonProperty("city")
	public String city;
	@JsonProperty("state")
	public String state;
	@JsonProperty("country_code")
	public String countryCode;
	@JsonProperty("longitude")
	public Double longitude;
	public final static long serialVersionUID = 5009493058335655529L;

}