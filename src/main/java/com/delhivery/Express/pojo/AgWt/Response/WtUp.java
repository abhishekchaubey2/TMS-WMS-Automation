package com.delhivery.Express.pojo.AgWt.Response;

import java.util.LinkedHashMap;
import java.util.Map;

import com.delhivery.Express.pojo.AgWt.Request.MaxWt;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonPropertyOrder({ "message", "success", "wbn" })
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WtUp {

	@JsonProperty("message")
	public String message;
	@JsonProperty("success")
	public Boolean success;
	@JsonProperty("wbn")
	public String wbn;

	@JsonProperty("error")
	public String error;

	@JsonIgnore
	public Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

}