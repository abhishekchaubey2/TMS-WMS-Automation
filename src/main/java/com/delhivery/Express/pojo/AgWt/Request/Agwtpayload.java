
package com.delhivery.Express.pojo.AgWt.Request;

import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)

public class Agwtpayload {

	@JsonProperty("action_date")
	public Long actionDate;
	@JsonProperty("max_wt")
	public MaxWt maxWt;
	@JsonProperty("mwn")
	public String mwn;
	@JsonProperty("waybill")
	public String waybill;
	@JsonProperty("wbn")
	public String wbn;
	@JsonProperty("wt_rule")
	public String wtRule;
	@JsonProperty("wt_type")
	public String wtType;
	@JsonProperty("wt")
	public Object wt;

}
