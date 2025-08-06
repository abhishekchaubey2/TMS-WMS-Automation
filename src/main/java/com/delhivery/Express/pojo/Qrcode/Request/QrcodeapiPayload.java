
package com.delhivery.Express.pojo.Qrcode.Request;

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

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)

public class QrcodeapiPayload {

	@JsonProperty("waybill")
	public String waybill;
	@JsonProperty("amount")
	public Object amount;
	@JsonProperty("client")
	public String client;
	@JsonProperty("gst")
	public Object gst;
	@JsonProperty("cgst")
	public Object cgst;
	@JsonProperty("sgst")
	public Object sgst;
	@JsonProperty("igst")
	public Object igst;
	@JsonProperty("cess")
	public Object cess;
	@JsonProperty("gst_incentive")
	public Object gstIncentive;
	@JsonProperty("gstpct")
	public Object gstpct;
	@JsonProperty("invoice_no")
	public Object invoiceNo;
	@JsonProperty("invoice_date")
	public Object invoiceDate;
	@JsonProperty("invoice_name")
	public Object invoiceName;
	@JsonProperty("gst_in")
	public Object gstIn;

}
