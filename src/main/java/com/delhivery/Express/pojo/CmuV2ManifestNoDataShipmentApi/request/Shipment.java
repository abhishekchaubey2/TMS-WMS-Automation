
package com.delhivery.Express.pojo.CmuV2ManifestNoDataShipmentApi.request;

import java.util.List;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "pin",
    "invoices",
    "order_info",
    "weight",
    "lrn",
    "phone",
    "payment_mode",
    "mps_children",
    "name",
    "city",
    "cod_amount",
    "mps_amount",
    "country",
    "state",
    "add",
    "product_type",
    "shipping"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Shipment {

    @JsonProperty("pin")
    public String pin;
    @JsonProperty("invoices")
    public List<Invoice> invoices = null;
    @JsonProperty("order_info")
    public List<Order_info> order_info = null;
    @JsonProperty("weight")
    public double weight;
    @JsonProperty("lrn")
    public String lrn;
    @JsonProperty("phone")
    public String phone;
    @JsonProperty("payment_mode")
    public String payment_mode;
    @JsonProperty("mps_children")
    public int mps_children;
    @JsonProperty("name")
    public String name;
    @JsonProperty("city")
    public String city;
    @JsonProperty("cod_amount")
    public int cod_amount;
    @JsonProperty("mps_amount")
    public int mps_amount;
    @JsonProperty("country")
    public String country;
    @JsonProperty("state")
    public String state;
    @JsonProperty("add")
    public String add;
    @JsonProperty("product_type")
    public String product_type;
    @JsonProperty("return_country")
    public String return_country;
    @JsonProperty("return_state")
    public String return_state;
    @JsonProperty("return_add")
    public String return_add;
    @JsonProperty("return_city")
    public String return_city;
    @JsonProperty("return_pin")
    public String return_pin;
    @JsonProperty("return_name")
    public String return_name;
    @JsonProperty("return_phone")
    public String return_phone;
    @JsonProperty("shipping_method")
    public String shippingMethod;
    

}
