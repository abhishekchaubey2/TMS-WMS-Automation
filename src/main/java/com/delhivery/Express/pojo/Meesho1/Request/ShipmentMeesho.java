package com.delhivery.Express.pojo.Meesho1.Request;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
@Builder
@Getter
@Setter
public class ShipmentMeesho {
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
    @JsonProperty("package_count")
    public Object packageCount;
    @JsonProperty("payment_mode")
    public Object paymentMode;
    @JsonProperty("product_type")
    public Object productType;
    @JsonProperty("qc")
    public Qc qc;
    @JsonProperty("extra_parameters")
    public ExtraParametersMeesho extraParameters;
    @JsonProperty("products_desc")
    public Object productsDesc;
    @JsonProperty("order_date")
    public Object orderDate;
    @JsonProperty("add")
    public Object add;
    @JsonProperty("cod_instructions")
    public Object codInstructions;
    @JsonProperty("total_amount")
    public Object totalAmount;
    @JsonProperty("country")
    public Object country;
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
    @JsonProperty("supplier")
    public Object supplier;
    @JsonProperty("billable_weight")
    public Object billableWeight;
    @JsonProperty("dimensions")
    public Object dimensions;
    @JsonProperty("volumetric")
    public Object volumetric;
    @JsonProperty("document_type")
    public Object documentType;
    @JsonProperty("document_number")
    public Object documentNumber;
    @JsonProperty("supply_sub_type")
    public Object supplySubType;
    @JsonProperty("hsn_code")
    public Object hsnCode;
    @JsonProperty("cod_amount")
    public Object codAmount;
}
