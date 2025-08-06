package com.delhivery.Express.pojo.Meesho3.Request;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.util.List;
@Builder
@Getter
@Setter

public class Shipment {
    @JsonProperty("client")
    public Object client;
    @JsonProperty("return_name")
    public Object returnName;
    @JsonProperty("order")
    public Object order;
    @JsonProperty("return_country")
    public Object returnCountry;
    @JsonProperty("weight")
    public Object weight;
    @JsonProperty("city")
    public Object city;
    @JsonProperty("pin")
    public Object pin;
    @JsonProperty("return_state")
    public Object returnState;
    @JsonProperty("products_desc")
    public Object productsDesc;
    @JsonProperty("shipping_mode")
    public Object shippingMode;
    @JsonProperty("state")
    public Object state;
    @JsonProperty("quantity")
    public Object quantity;
    @JsonProperty("phone")
    public Object phone;
    @JsonProperty("add")
    public Object add;
    @JsonProperty("payment_mode")
    public Object paymentMode;
    @JsonProperty("order_date")
    public Object orderDate;
    @JsonProperty("seller_gst_tin")
    public Object sellerGstTin;
    @JsonProperty("name")
    public Object name;
    @JsonProperty("return_add")
    public Object returnAdd;
    @JsonProperty("total_amount")
    public Object totalAmount;
    @JsonProperty("seller_name")
    public Object sellerName;
    @JsonProperty("return_city")
    public Object returnCity;
    @JsonProperty("country")
    public Object country;
    @JsonProperty("return_pin")
    public Object returnPin;
    @JsonProperty("return_phone")
    public Object returnPhone;
    @JsonProperty("custom_qc")
    public List<CustomQc> customQc;
}
