package com.delhivery.Express.pojo.UpdateTransId1.Request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

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
public class UpdateTransIdDexData {

	@JsonProperty("wbns_dict")
	private UpdateTransIdDexWbnsDict wbnsDict;
	@JsonProperty("cburl")
	private UpdateTransIdDexCburl cburl;
	@JsonProperty("action")
	private String action;
}