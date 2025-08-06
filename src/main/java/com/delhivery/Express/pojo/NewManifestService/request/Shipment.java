package com.delhivery.Express.pojo.NewManifestService.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;
@Builder
@Getter
@Setter

public class Shipment {
	@JsonProperty("name")
	public Object name;
	@JsonProperty("client")
	public Object client;
	@JsonProperty("waybill")
	public Object waybill;
	@JsonProperty("order")
	public Object order;
	@JsonProperty("products_desc")
	public Object productsDesc;
	@JsonProperty("order_date")
	public Object orderDate;
	@JsonProperty("payment_mode")
	public Object paymentMode;
	@JsonProperty("product_type")
	public Object productType;
	@JsonProperty("cod_amount")
	public Object codAmount;
	@JsonProperty("total_amount")
	public Object totalAmount;
	@JsonProperty("mps_amount")
	public Object mpsAmount;
	@JsonProperty("add")
	public Object add;
	@JsonProperty("city")
	public Object city;
	@JsonProperty("state")
	public Object state;
	@JsonProperty("country")
	public Object country;
	@JsonProperty("taxation_type")
	public Object taxationType;
	@JsonProperty("phone")
	public Object phone;
	@JsonProperty("pin")
	public Object pin;
	@JsonProperty("supplier")
	public Object supplier;
	@JsonProperty("billable_weight")
	public Object billableWeight;
	@JsonProperty("dimensions")
	public Object dimensions;
	@JsonProperty("volumetric")
	public Object volumetric;
	@JsonProperty("return_add")
	public Object returnAdd;
	@JsonProperty("return_state")
	public Object returnState;
	@JsonProperty("return_city")
	public Object returnCity;
	@JsonProperty("return_country")
	public Object returnCountry;
	@JsonProperty("return_name")
	public Object returnName;
	@JsonProperty("return_pin")
	public Object returnPin;
	@JsonProperty("fragile_shipment")
	public Object fragileShipment;
	@JsonProperty("document_number")
	public Object documentNumber;
	@JsonProperty("ewbn")
	public Object ewbn;
	@JsonProperty("source")
	public Object source;
	@JsonProperty("return_phone")
	public Object returnPhone;
	@JsonProperty("shipping_mode")
	public Object shippingMode;
	@JsonProperty("shipping_method")
	public Object shippingMethod;
	@JsonProperty("secondary_add")
	public Object secondaryAdd;
	@JsonProperty("secondary_city")
	public Object secondaryCity;
	@JsonProperty("secondary_state")
	public Object secondaryState;
	@JsonProperty("secondary_pin")
	public Object secondaryPin;
	@JsonProperty("secondary_country")
	public Object secondaryCountry;
	@JsonProperty("package_count")
	public Object packageCount;
	@JsonProperty("weight_verification")
	public Object weightVerification;
	@JsonProperty("essential_good")
	public Object essentialGood;
	@JsonProperty("tax_value")
	public Object taxValue;
	@JsonProperty("email")
	public Object email;
	@JsonProperty("weight")
	public Object weight;
	@JsonProperty("invoice_url")
	public Object invoiceUrl;
	@JsonProperty("quantity")
	public Object quantity;
	@JsonProperty("seller_name")
	public Object sellerName;
	@JsonProperty("seller_add")
	public Object sellerAdd;
	@JsonProperty("seller_city")
	public Object sellerCity;
	@JsonProperty("seller_state")
	public Object sellerState;
	@JsonProperty("seller_cst")
	public Object sellerCst;
	@JsonProperty("seller_tin")
	public Object sellerTin;
	@JsonProperty("seller_inv")
	public Object sellerInv;
	@JsonProperty("seller_inv_date")
	public Object sellerInvDate;
	@JsonProperty("shipment_length")
	public Object shipmentLength;
	@JsonProperty("shipment_width")
	public Object shipmentWidth;
	@JsonProperty("shipment_height")
	public Object shipmentHeight;
	@JsonProperty("consignee_tin")
	public Object consigneeTin;
	@JsonProperty("commodity_value")
	public Object commodityValue;
	@JsonProperty("category_of_goods")
	public Object categoryOfGoods;
	@JsonProperty("dangerous_goods")
	public Object dangerousGoods;
	@JsonProperty("shipment_type")
	public Object shipmentType;
	@JsonProperty("seller_gst_tin")
	public Object sellerGstTin;
	@JsonProperty("client_gst_tin")
	public Object clientGstTin;
	@JsonProperty("consignee_gst_tin")
	public Object consigneeGstTin;
	@JsonProperty("hsn_code")
	public Object hsnCode;
	@JsonProperty("invoice_reference")
	public Object invoiceReference;
	@JsonProperty("od_distance")
	public Object odDistance;
	@JsonProperty("billing_name")
	public Object billingName;
	@JsonProperty("country_code")
	public Object countryCode;
	@JsonProperty("clearance_mode")
	public Object clearanceMode;
	@JsonProperty("fm_address")
	public Object fmAddress;
	@JsonProperty("fm_phone")
	public Object fmPhone;
	@JsonProperty("fm_pin")
	public Object fmPin;
	@JsonProperty("transport_speed")
	public Object transportSpeed;
	@JsonProperty("master_id")
	public Object masterId;
	@JsonProperty("pickup_start_time")
	public Object pickupStartTime;
	@JsonProperty("pickup_end_time")
	public Object pickupEndTime;
	@JsonProperty("pickup_slot_code")
	public Object pickupSlotCode;
	@JsonProperty("drop_start_time")
	public Object dropStartTime;
	@JsonProperty("drop_end_time")
	public Object dropEndTime;
	@JsonProperty("drop_slot_code")
	public Object dropSlotCode;
	@JsonProperty("service_type")
	public Object serviceType;
	@JsonProperty("sales_tax_form_ack_no")
	public Object salesTaxFormAckNo;
	@JsonProperty("mps_children")
	public Object mpsChildren;
	@JsonProperty("mps_weight")
	public Object mpsWeight;
	@JsonProperty("mps_vweight")
	public Object mpsVweight;
	@JsonProperty("master id")
	public Object masterid;
	@JsonProperty("pickup end time")
	public Object pickupendtime;
	@JsonProperty("pickup slot code")
	public Object pickupslotcode;
	@JsonProperty("drop start time")
	public Object dropstarttime;
	@JsonProperty("drop end time")
	public Object dropendtime;
	@JsonProperty("drop slot code")
	public Object dropslotcode;
	@JsonProperty("product type")
	public Object producttype;
	@JsonProperty("service type")
	public Object servicetype;
	

}
