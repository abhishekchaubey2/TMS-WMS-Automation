package com.delhivery.Express.pojo.ewbnCreate.request;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
//builder for tripitemss
//following are the fields in tripitems class
public class TripItem {
    @JsonProperty("billToState")
    public String billToState;
    @JsonProperty("distance")
    public Integer distance;
    @JsonProperty("hsnCode")
    public String hsnCode;
    @JsonProperty("consigneeName")
    public String consigneeName;
    @JsonProperty("invoiceVal")
    public double invoiceVal;
    @JsonProperty("ewaybill")
    public String ewaybill;
    @JsonProperty("consignorPin")
    public String consignorPin;
    @JsonProperty("consignorAddr")
    public String consignorAddr;
    @JsonProperty("invoiceNo")
    public String invoiceNo;
    @JsonProperty("unit")
    public String unit;
    @JsonProperty("consigneeAddr")
    public String consigneeAddr;
    @JsonProperty("consignorGst")
    public String consignorGst;
    @JsonProperty("consignorName")
    public String consignorName;
    @JsonProperty("consigneeGst")
    public String consigneeGst;
    @JsonProperty("invoiceDt")
    public String invoiceDt;
    @JsonProperty("qty")
    public Integer qty;
    @JsonProperty("awb")
    public String awb;
    @JsonProperty("consigneePin")
    public String consigneePin;
    @JsonProperty("shipToState")
    public String shipToState;
    @JsonProperty("transactionType")
    public String transactionType;
}
