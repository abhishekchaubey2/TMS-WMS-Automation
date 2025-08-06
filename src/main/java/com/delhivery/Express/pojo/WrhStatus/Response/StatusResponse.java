
package com.delhivery.Express.pojo.WrhStatus.Response;

import java.util.LinkedHashMap;

import java.util.Map;

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

public class StatusResponse {

	@JsonProperty("data")
	public Data data;
	@JsonProperty("success")
	public Boolean success;
	@JsonProperty("error")
	public String error;

}
