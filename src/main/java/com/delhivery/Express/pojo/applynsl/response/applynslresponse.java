package com.delhivery.Express.pojo.applynsl.response;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

public class applynslresponse {
	@JsonProperty("success")
	public Boolean success;
	@JsonProperty("error")
	public String error;

}
