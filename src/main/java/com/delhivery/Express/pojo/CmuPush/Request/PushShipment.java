package com.delhivery.Express.pojo.CmuPush.Request;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "client",
    "order",
    "products_desc",
    "order_date",
    "payment_mode",
    "product_type",
    "cod_amount",
    "total_amount",
    "package_count",
    "add",
    "city",
    "state",
    "country",
    "client_gst_tin",
    "consignee_gst_tin",
    "hsn_code",
    "invoice_reference",
    "seller_gst_tin",
    "item_desc",
    "phone",
    "pin",
    "dangerous_good",
    "return_add",
    "return_state",
    "return_city",
    "return_country",
    "return_name",
    "return_pin",
    "return_phone",
    "supplier",
    "billable_weight",
    "dimensions",
    "volumetric",
    "weight",
    "fm_phone"
})
@Builder
@Getter
@Setter
public class PushShipment {

    @JsonProperty("name")
    public String name;
    @JsonProperty("client")
    public String client;
    @JsonProperty("order")
    public String order;
    @JsonProperty("products_desc")
    public String productsDesc;
    @JsonProperty("order_date")
    public String orderDate;
    @JsonProperty("payment_mode")
    public String paymentMode;
    @JsonProperty("product_type")
    public String productType;
    @JsonProperty("cod_amount")
    public long codAmount;
    @JsonProperty("total_amount")
    public long totalAmount;
    @JsonProperty("package_count")
    public long packageCount;
    @JsonProperty("add")
    public String add;
    @JsonProperty("city")
    public String city;
    @JsonProperty("state")
    public String state;
    @JsonProperty("country")
    public String country;
    @JsonProperty("client_gst_tin")
    public String clientGstTin;
    @JsonProperty("consignee_gst_tin")
    public String consigneeGstTin;
    @JsonProperty("hsn_code")
    public String hsnCode;
    @JsonProperty("invoice_reference")
    public String invoiceReference;
    @JsonProperty("seller_gst_tin")
    public String sellerGstTin;
    @JsonProperty("item_desc")
    public List<ItemDesc> itemDesc;
    @JsonProperty("phone")
    public String phone;
    @JsonProperty("pin")
    public String pin;
    @JsonProperty("dangerous_good")
    public boolean dangerousGood;
    @JsonProperty("return_add")
    public String returnAdd;
    @JsonProperty("return_state")
    public String returnState;
    @JsonProperty("return_city")
    public String returnCity;
    @JsonProperty("return_country")
    public String returnCountry;
    @JsonProperty("return_name")
    public String returnName;
    @JsonProperty("return_pin")
    public String returnPin;
    @JsonProperty("return_phone")
    public String returnPhone;
    @JsonProperty("supplier")
    public String supplier;
    @JsonProperty("billable_weight")
    public long billableWeight;
    @JsonProperty("dimensions")
    public String dimensions;
    @JsonProperty("volumetric")
    public long volumetric;
    @JsonProperty("weight")
    public String weight;
    @JsonProperty("fm_phone")
    public String fmPhone;
    @JsonProperty("shipping_method")
    public String shippingMethod;

}
