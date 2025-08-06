
package com.delhivery.Express.pojo.FetchList.Response;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.delhivery.Express.pojo.FetchList.Requist.FetchListRequest;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@JsonPropertyOrder({ "packages", "error_code", "success", "error" })
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FetchListResponse {

	@JsonProperty("packages")
	public List<Object> packages;
	@JsonProperty("error_code")
	public String errorCode;
	@JsonProperty("success")
	public Boolean success;
	@JsonProperty("error")
	public String error;
	@JsonIgnore
	public Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

}
