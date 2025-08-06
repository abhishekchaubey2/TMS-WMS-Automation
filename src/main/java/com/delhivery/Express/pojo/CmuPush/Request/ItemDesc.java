package com.delhivery.Express.pojo.CmuPush.Request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "supplier",
    "seller_inv",
    "seller_name",
    "seller_add",
    "seller_cst",
    "seller_tin",
    "seller_inv_date",
    "consignee_tin",
    "commodity_value",
    "tax_value",
    "sales_tax_form_ack_no",
    "category_of_goods",
    "seller_gst_tin"
})
@Builder
@Getter
@Setter
public class ItemDesc {

    @JsonProperty("supplier")
    public String supplier;
    @JsonProperty("seller_inv")
    public String sellerInv;
    @JsonProperty("seller_name")
    public String sellerName;
    @JsonProperty("seller_add")
    public String sellerAdd;
    @JsonProperty("seller_cst")
    public String sellerCst;
    @JsonProperty("seller_tin")
    public String sellerTin;
    @JsonProperty("seller_inv_date")
    public String sellerInvDate;
    @JsonProperty("consignee_tin")
    public String consigneeTin;
    @JsonProperty("commodity_value")
    public String commodityValue;
    @JsonProperty("tax_value")
    public String taxValue;
    @JsonProperty("sales_tax_form_ack_no")
    public String salesTaxFormAckNo;
    @JsonProperty("category_of_goods")
    public String categoryOfGoods;
    @JsonProperty("seller_gst_tin")
    public String sellerGstTin;

}
