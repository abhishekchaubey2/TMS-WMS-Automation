package com.delhivery.Express.pojo.NewManifestMadatoryKeys.request;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
//POJO containg only mandatory keys


public class Shipments {
    @JsonProperty("client")
    public Object client;
    @JsonProperty("order")
    public Object order;
    @JsonProperty("product_type")
    public Object productType;
    @JsonProperty("pin")
    public Object pin;
    @JsonProperty("phone")
    public Object phone;
    @JsonProperty("add")
    public Object add;
    @JsonProperty("payment_mode")
    public Object paymentMode;
    @JsonProperty("name")
    public Object name;

}
