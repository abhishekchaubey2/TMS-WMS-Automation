package com.delhivery.Express.pojo.applynslgeneric.request;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@Getter
@Setter
@Builder

public class Wbn {
	@JsonProperty("Waybill")
	public Waybill waybill;

}
