package com.delhivery.Express.pojo.applynsl.request;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
public class applynsl {
	@JsonProperty("wbns")
	public List<String> wbns;
	@JsonProperty("nsl")
	public String nsl;

}
