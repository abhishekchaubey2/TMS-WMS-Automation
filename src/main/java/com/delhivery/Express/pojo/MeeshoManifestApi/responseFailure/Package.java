
package com.delhivery.Express.pojo.MeeshoManifestApi.responseFailure;

import java.util.List;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "status",
    "waybill",
    "refnum",
    "client",
    "remarks",
    "cod_amount",
    "payment"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Package {

	@JsonProperty("status")
	public String status;
	@JsonProperty("err_code")
	public String errCode;
	@JsonProperty("client")
	public String client;
	@JsonProperty("sort_code")
	public Object sortCode;
	@JsonProperty("remarks")
	public List<String> remarks;
	@JsonProperty("waybill")
	public String waybill;
	@JsonProperty("cod_amount")
	public Float codAmount;
	@JsonProperty("payment")
	public String payment;
	@JsonProperty("serviceable")
	public Object serviceable;
	@JsonProperty("refnum")
	public String refnum;

}
