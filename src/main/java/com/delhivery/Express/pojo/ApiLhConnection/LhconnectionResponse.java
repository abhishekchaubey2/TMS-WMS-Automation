package com.delhivery.Express.pojo.ApiLhConnection;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.delhivery.Express.pojo.AgWt.Request.MaxWt;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonPropertyOrder({ "data", "success", "error" })
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LhconnectionResponse {

	@JsonProperty("data")
	public List<Datum> data;
	@JsonProperty("success")
	public Boolean success;
	@JsonProperty("error")
	public String error;
	@JsonIgnore
	public Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

}
