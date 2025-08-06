package com.delhivery.Express.pojo.EditApi.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EditApiCancellationScanResponsePayload {
	@JsonProperty("status")
	public String status;
	@JsonProperty("waybill")
	public String waybill;
	@JsonProperty("order_id")
	public String orderId;
	@JsonProperty("remark")
	public String remark;


}