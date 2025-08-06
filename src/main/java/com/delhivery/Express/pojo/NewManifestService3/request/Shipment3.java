package com.delhivery.Express.pojo.NewManifestService3.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;
@Builder
@Getter
@Setter

public class Shipment3 {
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
	@JsonProperty("product type")
	public Object producttype;
	@JsonProperty("e2e_id")
	public Object e2eId;
	@JsonProperty("cheque_add")
	public Object chequeAdd;
	@JsonProperty("cheque_phone")
	public Object chequePhone;
	@JsonProperty("cheque address")
	public Object chequeaddress;
	@JsonProperty("cheque phone")
	public Object chequephones;
	@JsonProperty("cheque city")
	public Object chequecitys;
	@JsonProperty("cheque_city")
	public Object chequeCity;
	@JsonProperty("cheque_state")
	public Object chequeState;
	@JsonProperty("cheque state")
	public Object chequestates;
	@JsonProperty("cheque_pin")
	public Object chequePin;
	@JsonProperty("cheque pincode")
	public Object chequepincode;
	@JsonProperty("cheque_country")
	public Object chequeCountry;
	@JsonProperty("cheque country")
	public Object chequecountrys;
	@JsonProperty("supply Type")
	public Object supplyType;
	@JsonProperty("subSupplyDesc")
	public Object subSupplyDesc;
	@JsonProperty("transactionType")
	public Object transactionType;
	@JsonProperty("otherValue")
	public Object otherValue;
	@JsonProperty("cessNonAdvolValue")
	public Object cessNonAdvolValue;
	@JsonProperty("cessNonAdvolRate")
	public Object cessNonAdvolRate;
	@JsonProperty("provider")
	public Object provider;
	@JsonProperty("hyperlocal")
	public Object hyperlocal;
	@JsonProperty("plastic_packaging")
	public Object plasticPackaging;
	@JsonProperty("flat_rate_type")
	public Object flatRateType;
	@JsonProperty("hold_location")
	public Object holdLocation;
	@JsonProperty("same_day_clear")
	public Object sameDayClear;
	@JsonProperty("fm_pickup_type")
	public Object fmPickupType;
	@JsonProperty("fm_ucid")
	public Object fmUcid;
	@JsonProperty("insured")
	public Object insured;
	@JsonProperty("has_document")
	public Object hasDocument;
	@JsonProperty("verified_add")
	public Object verifiedAdd;
	@JsonProperty("einv_qr")
	public Object einvQr;
	@JsonProperty("manifestation_user")
	public Object manifestationUser;
	@JsonProperty("item_desc")
	public Object itemDesc;
	@JsonProperty("invoice_value")
	public Object invoiceValue;
	@JsonProperty("supply_sub_type")
	public Object supplySubType;
	@JsonProperty("document_type")
	public Object documentType;
	@JsonProperty("document_date")
	public Object documentDate;
	@JsonProperty("billing_add")
	public Object billingAdd;
	@JsonProperty("billing_pin")
	public Object billingPin;
	@JsonProperty("supply_type")
	public Object supplytypes;
	@JsonProperty("sub_supply_type")
	public Object subSupplyType;
	@JsonProperty("transaction_type")
	public Object transactiontype;
	@JsonProperty("other_value")
	public Object othervalues;
	@JsonProperty("cess_non_advol_value")
	public Object cessnonadvolvalues;
	@JsonProperty("pri_number")
	public Object priNumber;
	@JsonProperty("unique_customer_id")
	public Object uniqueCustomerId;


}
