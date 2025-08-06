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
    "gm",
    "o_pin",
    "pt",
    "d_pin"
})

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Params {

    @JsonProperty("gm")
    public String gm;
    @JsonProperty("o_pin")
    public String oPin;
    @JsonProperty("pt")
    public String pt;
    @JsonProperty("d_pin")
    public String dPin;

}
