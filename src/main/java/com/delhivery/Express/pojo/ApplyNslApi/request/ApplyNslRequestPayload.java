package com.delhivery.Express.pojo.ApplyNslApi.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)

public class ApplyNslRequestPayload {
	@JsonProperty("nsl_id")
	public String nslId;
	@JsonProperty("scope")
	public String scope;
	@JsonProperty("status_type")
	public String statusType;
	@JsonProperty("status")
	public String status;
	@JsonProperty("wbns")
	public List<String> wbns = null;
	@JsonProperty("user")
	public String user;
}
