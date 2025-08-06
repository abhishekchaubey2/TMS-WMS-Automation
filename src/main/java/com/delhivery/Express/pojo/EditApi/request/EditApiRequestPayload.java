package com.delhivery.Express.pojo.EditApi.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EditApiRequestPayload {
	@JsonProperty("waybill")
	public String waybill;
	@JsonProperty("tax_value")
	public String taxValue;
	@JsonProperty("shipment_width")
	public double shipmentWidth;
	@JsonProperty("product_category")
	public String productCategory;
	@JsonProperty("consignee_tin")
	public String consigneeTin;
	@JsonProperty("stax_ack_number")
	public String staxAckNumber;
	@JsonProperty("gm")
	public double gm;
	@JsonProperty("shipment_length")
	public double shipmentLength;
	@JsonProperty("shipment_height")
	public double shipmentHeight;
	@JsonProperty("commodity_value")
	public String commodityValue;
	@JsonProperty("name")
	public String name;
	@JsonProperty("product_details")
	public String productDetails;
	@JsonProperty("add")
	public String add;
	@JsonProperty("phone")
	public String phone;
	@JsonProperty("return_add")
	public String returnAdd;
	@JsonProperty("pincode")
	public int pincode;
	@JsonProperty("cancellation")
	public String cancellation;
	@JsonProperty("rs")
	public double rs;
}
