package com.delhivery.Express.pojo.NewManifestService2.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;
@Builder
@Getter
@Setter

public class Shipment2 {
	@JsonProperty("name")
	public Object name;
	@JsonProperty("client")
	public Object client;
	@JsonProperty("waybill")
	public Object waybill;
	@JsonProperty("order")
	public Object order;
	@JsonProperty("payment_mode")
	public Object paymentMode;
	@JsonProperty("add")
	public Object add;
	@JsonProperty("phone")
	public Object phone;
	@JsonProperty("pin")
	public Object pin;
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
	//add pickup start time
	@JsonProperty("pickup start time")
	public Object pickupstarttime;
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
	@JsonProperty("time bound")
	public Object timebound;
	@JsonProperty("quality check")
	public Object qualitycheck;
	@JsonProperty("taxation type")
	public Object taxationtype;
	@JsonProperty("fod")
	public Object fod;
	@JsonProperty("delivery_date")
	public Object deliveryDate;
	@JsonProperty("next_trial_date")
	public Object nextTrialDate;
	@JsonProperty("movement_geography")
	public Object movementGeography;
	@JsonProperty("fm_mode")
	public Object fmMode;
	@JsonProperty("flow_type")
	public Object flowType;
	@JsonProperty("product_category")
	public Object productCategory;
	@JsonProperty("speed")
	public Object speed;
	@JsonProperty("vas")
	public Object vas;
	@JsonProperty("ready_to_ship")
	public Object readyToShip;
	@JsonProperty("internal")
	public Object internal;
	@JsonProperty("prime")
	public Object prime;
	@JsonProperty("delivery_instructions")
	public Object deliveryInstructions;
	@JsonProperty("lrn")
	public Object lrn;
	@JsonProperty("qc")
	public Object qc;
	@JsonProperty("consignee_gst_amount")
	public Object consigneeGstAmount;
	@JsonProperty("seller_gst_amount")
	public Object sellerGstAmount;
	@JsonProperty("integrated_gst_amount")
	public Object integratedGstAmount;
	@JsonProperty("gst_cess_amount")
	public Object gstCessAmount;
	@JsonProperty("product")
	public Object product;
	@JsonProperty("product_desc")
	public Object productDesc;
	@JsonProperty("product_quantity")
	public Object productQuantity;
	@JsonProperty("unit")
	public Object unit;
	@JsonProperty("consignee_gst_rate")
	public Object consigneeGstRate;
	@JsonProperty("seller_gst_rate")
	public Object sellerGstRate;
	@JsonProperty("integrated_gst_rate")
	public Object integratedGstRate;
	@JsonProperty("cess_gst_rate")
	public Object cessGstRate;
	@JsonProperty("address_type")
	public Object addressType;
	@JsonProperty("multi_inv_amt")
	public Object multiInvAmt;
	@JsonProperty("cod_instructions")
	public Object codInstructions;
	@JsonProperty("buyback_address")
	public Object buybackAddress;
	@JsonProperty("buyback_pin")
	public Object buybackPin;
	@JsonProperty("buyback_description")
	public Object buybackDescription;
	@JsonProperty("ship_option")
	public Object shipOption;
	@JsonProperty("estimated_arrival_date")
	public Object estimatedArrivalDate;
	@JsonProperty("ship_method")
	public Object shipMethod;
	@JsonProperty("buyback_amount")
	public Object buybackAmount;
	@JsonProperty("is_secure")
	public Object isSecure;
	@JsonProperty("person_specific")
	public Object personSpecific;
	@JsonProperty("address_specific")
	public Object addressSpecific;
	@JsonProperty("check_one_secure")
	public Object checkOneSecure;
	@JsonProperty("otp")
	public Object otp;
	@JsonProperty("shipment_code")
	public Object shipmentCode;
	@JsonProperty("cc")
	public Object cc;
	@JsonProperty("cod")
	public Object cod;
	@JsonProperty("customs")
	public Object customs;
	@JsonProperty("waiver")
	public Object waiver;
	@JsonProperty("return_store_location")
	public Object returnStoreLocation;
	@JsonProperty("pickup_payment_mode")
	public Object pickupPaymentMode;
	@JsonProperty("cop_amount")
	public Object copAmount;
	@JsonProperty("preferred_days")
	public Object preferredDays;
	@JsonProperty("expected_pcount")
	public Object expectedPcount;
	@JsonProperty("is_otp_verified")
	public Object isOtpVerified;
	@JsonProperty("freight_mode")
	public Object freightMode;
	@JsonProperty("freight_charge")
	public Object freightCharge;
	@JsonProperty("land_mark")
	public Object landMark;
	@JsonProperty("address_id")
	public Object addressId;
	@JsonProperty("customer_id")
	public Object customerId;
	@JsonProperty("international")
	public Object international;
	@JsonProperty("is_cod")
	public Object isCod;
	
}
