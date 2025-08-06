package com.delhivery.Express.pojo.applynslgeneric.response;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ApplynslgenericResponse {
	@JsonProperty("msg")
	public String msg;
	@JsonProperty("upl")
	public String upl;
	@JsonProperty("success")
	public Boolean success;

}
