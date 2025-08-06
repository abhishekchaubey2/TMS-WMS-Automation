
package com.delhivery.Express.pojo.WrhCreate.Response;

import java.util.List;

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
public class Data {

	@JsonProperty("business_hours")
	public BusinessHours businessHours;
	@JsonProperty("name")
	public String name;
	@JsonProperty("business_days")
	public List<String> businessDays;
	@JsonProperty("pincode")
	public Integer pincode;
	@JsonProperty("type_of_clientwarehouse")
	public Object typeOfClientwarehouse;
	@JsonProperty("phone")
	public String phone;
	@JsonProperty("client")
	public String client;
	@JsonProperty("address")
	public String address;
	@JsonProperty("active")
	public Boolean active;
	@JsonProperty("message")
	public String message;
	@JsonProperty("largest_vehicle_constraint")
	public Object largestVehicleConstraint;

}
