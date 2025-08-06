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
    "DL",
    "FS",
    "DTO",
    "RTO",
    "ST",
    "COD",
    "TDS",
    "CARD",
    "CNC"
})

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AllCharges {

    @JsonProperty("DL")
    public long dl;
    @JsonProperty("FS")
    public long fs;
    @JsonProperty("DTO")
    public long dto;
    @JsonProperty("RTO")
    public long rto;
    @JsonProperty("ST")
    public float st;
    @JsonProperty("COD")
    public long cod;
    @JsonProperty("TDS")
    public long tds;
    @JsonProperty("CARD")
    public long card;
    @JsonProperty("CNC")
    public long cnc;

}
