package com.delhivery.Express.pojo.AgWt.Response;

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

	@JsonProperty("action_date")
	private Long actionDate;
	@JsonProperty("max_wt")
	private MaxWt maxWt;
	@JsonProperty("mwn")
	private String mwn;
	@JsonProperty("waybill")
	private String waybill;
	@JsonProperty("wt")
	private Long wt;
	@JsonProperty("wt_rule")
	private String wtRule;
	@JsonProperty("wt_type")
	private String wtType;

}
