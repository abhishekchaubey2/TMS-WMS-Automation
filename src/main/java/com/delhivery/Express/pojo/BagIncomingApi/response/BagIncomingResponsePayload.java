package com.delhivery.Express.pojo.BagIncomingApi.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class BagIncomingResponsePayload {
	@JsonProperty("data")
	private BagIncomingData data;
	@JsonProperty("bi")
	private String bi;
	@JsonProperty("success")
	private Boolean success;
	@JsonProperty("error")
	private String error;

}