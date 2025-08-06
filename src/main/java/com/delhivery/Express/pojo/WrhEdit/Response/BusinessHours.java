
package com.delhivery.Express.pojo.WrhEdit.Response;

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
public class BusinessHours {

	@JsonProperty("WED")
	private Wed wed;
	@JsonProperty("THU")
	private Thu thu;
	@JsonProperty("FRI")
	private Fri fri;
	@JsonProperty("MON")
	private Mon mon;
	@JsonProperty("TUE")
	private Tue tue;
	@JsonProperty("SAT")
	private Sat sat;

}
