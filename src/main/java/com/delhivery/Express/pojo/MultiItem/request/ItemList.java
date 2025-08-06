package com.delhivery.Express.pojo.MultiItem.request;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter

//pojo for item list in shipment dict
//following keys for item list for request builder
public class ItemList {
    @JsonProperty("product")
    public Object product;
    @JsonProperty("description")
    public Object description;
    @JsonProperty("hsn_code")
    public Object hsnCode;
    @JsonProperty("ewbn")
    public Object ewbn;
    @JsonProperty("document_number")
    public Object documentNumber;
    @JsonProperty("taxable_amount")
    public Object taxableAmount;
    @JsonProperty("quantity")
    public Object quantity;
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
}
