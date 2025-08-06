package com.delhivery.Express.pojo.QcWtApi.request;

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
public class QcWtPayload {
	@JsonProperty("wbn")
	public String wbn;
	@JsonProperty("h")
	public double h;
	@JsonProperty("b")
	public double b;
	@JsonProperty("l")
	public double l;
	@JsonProperty("wt")
	public double wt;
	@JsonProperty("wt_rule")
	public String wtRule;
	@JsonProperty("v")
	public double v;
	@JsonProperty("rv")
	public double rv;
	@JsonProperty("wt_type")
	public String wtType;
	@JsonProperty("int_wt_iwt")
	public double intWtIwt;
	@JsonProperty("source")
	public String source;
}
