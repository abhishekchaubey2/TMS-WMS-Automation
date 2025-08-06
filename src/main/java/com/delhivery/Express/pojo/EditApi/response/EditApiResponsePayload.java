package com.delhivery.Express.pojo.EditApi.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EditApiResponsePayload {
	@JsonProperty("status")
	public String status;
	@JsonProperty("waybill")
	public String waybill;
	@JsonProperty("order_id")
	public String orderId;
	@JsonProperty("error")
	public String error;


}