package com.delhivery.Express.pojo.applynslgeneric.request;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@Getter
@Setter
@Builder

public class Waybill {

	@JsonProperty("nsl_code")
	public String nslCode;
	@JsonProperty("nsl_remark")
	public String nslRemark;
}
