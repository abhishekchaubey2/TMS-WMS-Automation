
package com.delhivery.Express.pojo.Qrcode.Response;

import java.util.LinkedHashMap;
import java.util.Map;

import com.delhivery.Express.pojo.AgWt.Request.MaxWt;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "payment_id", "success", "wallet_details", "mode", "qr_details", "card_details", "plod_details" })
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class QrcodeapiResponse {

	@JsonProperty("payment_id")
	public String paymentId;
	@JsonProperty("success")
	public Boolean success;
	@JsonProperty("wallet_details")
	public WalletDetails walletDetails;
	@JsonProperty("mode")
	public String mode;
	@JsonProperty("qr_details")
	public QrDetails qrDetails;
	@JsonProperty("card_details")
	public CardDetails cardDetails;
	@JsonProperty("plod_details")
	public PlodDetails plodDetails;
	@JsonIgnore
	public Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

}
