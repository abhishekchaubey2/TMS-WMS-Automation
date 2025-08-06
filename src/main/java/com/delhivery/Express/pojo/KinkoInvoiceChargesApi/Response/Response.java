package com.delhivery.Express.pojo.KinkoInvoiceChargesApi.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "delivery_charges",
    "return_charges",
    "all_charges",
    "canc_charges",
    "pickup_charges",
    "params"
})

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    @JsonProperty("delivery_charges")
    public long deliveryCharges;
    @JsonProperty("return_charges")
    public long returnCharges;
    @JsonProperty("all_charges")
    public AllCharges allCharges;
    @JsonProperty("canc_charges")
    public long cancCharges;
    @JsonProperty("pickup_charges")
    public long pickupCharges;
    @JsonProperty("params")
    public Params params;

}
