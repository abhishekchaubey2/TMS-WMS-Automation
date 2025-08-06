package com.delhivery.Express.pojo.ApiPhonelog;

import java.util.List;
import lombok.*;

import com.delhivery.Express.pojo.AgWt.Request.MaxWt;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)

public class PhonelogRequest {

	@JsonProperty("edit_page")
	public Object edit_page;
	@JsonProperty("waybill")
	public String waybill;
	@JsonProperty("client")
	public String client;
	@JsonProperty("consignee")
	public Object consignee;
	@JsonProperty("phoneNumber")
	public Object phoneNumber;

}