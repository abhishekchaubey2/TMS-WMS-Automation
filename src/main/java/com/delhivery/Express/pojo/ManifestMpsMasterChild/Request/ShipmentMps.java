package com.delhivery.Express.pojo.ManifestMpsMasterChild.Request;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter


//ShipmentMps request builder keys
public class ShipmentMps {
    @JsonProperty("lrn")
    public Object lrn;
    @JsonProperty("product_type")
    public Object productType;
    @JsonProperty("mps_children")
    public Object mpsChildren;
    @JsonProperty("fod")
    public Object fod;
    @JsonProperty("payment_mode")
    public Object paymentMode;
    @JsonProperty("cod_amount")
    public Object codAmount;
    @JsonProperty("weight")
    public Object weight;
    @JsonProperty("taxation_type")
    public Object taxationType;
    @JsonProperty("shipment_type")
    public Object shipmentType;
    @JsonProperty("source")
    public Object source;
    @JsonProperty("supplier")
    public Object supplier;
    @JsonProperty("name")
    public Object name;
    @JsonProperty("phone")
    public Object phone;
    @JsonProperty("add")
    public Object add;
    @JsonProperty("pin")
    public Object pin;
    @JsonProperty("city")
    public Object city;
    @JsonProperty("state")
    public Object state;
    @JsonProperty("products_desc")
    public Object productsDesc;
    @JsonProperty("order")
    public Object order;
    @JsonProperty("invoice_reference")
    public Object invoiceReference;
    @JsonProperty("seller_cst")
    public Object sellerCst;
    @JsonProperty("seller_tin")
    public Object sellerTin;
    @JsonProperty("seller_gst_tin")
    public Object sellerGstTin;
    @JsonProperty("client_gst_tin")
    public Object clientGstTin;
    @JsonProperty("consignee_gst_tin")
    public Object consigneeGstTin;
    @JsonProperty("total_amount")
    public Object totalAmount;
    @JsonProperty("client")
    public Object client;
    @JsonProperty("order_date")
    public Object orderDate;
    @JsonProperty("mps_amount")
    public Object mpsAmount;
    @JsonProperty("master_id")
    public Object masterId;
    @JsonProperty("waybill")
    public Object waybill;
    @JsonProperty("ewbn")
    public Object ewbn;
    @JsonProperty("country")
    public Object country;
    @JsonProperty("sst")
    public Object sst;
    @JsonProperty("hsn_code")
    public Object hsnCode;
    @JsonProperty("supply_sub_type")
    public Object supplySubType;
    @JsonProperty("document_type")
    public Object documentType;
    @JsonProperty("seller_name")
    public Object sellerName;
    @JsonProperty("document_number")
    public Object documentNumber;
    @JsonProperty("multi_inv_amt")
    public Object multiInvAmt;
    @JsonProperty("internal")
    public Object internal;
}
