
package com.delhivery.Express.pojo.WrhStatus.Response;

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

	@JsonProperty("active")
	private Boolean active;
	@JsonProperty("working_hours")
	private WorkingHours workingHours;
	@JsonProperty("name")
	private String name;
	@JsonProperty("type_of_clientwarehouse")
	private Object typeOfClientwarehouse;
	@JsonProperty("message")
	private String message;
	@JsonProperty("pincode")
	private Integer pincode;
	@JsonProperty("largest_vehicle_constraint")
	private Object largestVehicleConstraint;

}
