package com.delhivery.Express.pojo.MultiItem.request;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter


//pojo for shipment dict in multiitemRequest

public class MultiItemShipment {
    @JsonProperty("od_distance")
    public Object odDistance;
    @JsonProperty("waybill")
    public Object waybill;
    @JsonProperty("client")
    public Object client;
    @JsonProperty("name")
    public Object name;
    @JsonProperty("order")
    public Object order;
    @JsonProperty("product_type")
    public Object productType;
    @JsonProperty("products_desc")
    public Object productsDesc;
    @JsonProperty("order_date")
    public Object orderDate;
    @JsonProperty("payment_mode")
    public Object paymentMode;
    @JsonProperty("cod_amount")
    public Object codAmount;
    @JsonProperty("total_amount")
    public Object totalAmount;
    @JsonProperty("add")
    public Object add;
    @JsonProperty("city")
    public Object city;
    @JsonProperty("country")
    public Object country;
    @JsonProperty("state")
    public Object state;
    @JsonProperty("client_gst_tin")
    public Object clientGstTin;
    @JsonProperty("consignee_gst_tin")
    public Object consigneeGstTin;
    @JsonProperty("invoice_reference")
    public Object invoiceReference;
    @JsonProperty("seller_gst_tin")
    public Object sellerGstTin;
    @JsonProperty("seller_name")
    public Object sellerName;
    @JsonProperty("phone")
    public Object phone;
    @JsonProperty("pin")
    public Object pin;
    @JsonProperty("dangerous_good")
    public Object dangerousGood;
    @JsonProperty("return_city")
    public Object returnCity;
    @JsonProperty("return_country")
    public Object returnCountry;
    @JsonProperty("return_name")
    public Object returnName;
    @JsonProperty("return_phone")
    public Object returnPhone;
    @JsonProperty("return_pin")
    public Object returnPin;
    @JsonProperty("return_state")
    public Object returnState;
    @JsonProperty("supplier")
    public Object supplier;
    @JsonProperty("billable_weight")
    public Object billableWeight;
    @JsonProperty("dimensions")
    public Object dimensions;
    @JsonProperty("volumetric")
    public Object volumetric;
    @JsonProperty("weight")
    public Object weight;
    @JsonProperty("document_type")
    public Object documentType;
    @JsonProperty("document_date")
    public Object documentDate;
    @JsonProperty("supply_sub_type")
    public Object supplySubType;
    @JsonProperty("items")
    public List<ItemList> items;

}
