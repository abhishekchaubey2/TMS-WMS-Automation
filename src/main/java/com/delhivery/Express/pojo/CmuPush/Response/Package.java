package com.delhivery.Express.pojo.CmuPush.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({
    "status",
    "waybill",
    "serviceable",
    "refnum",
    "client",
    "remarks",
    "sort_code",
    "cod_amount",
    "payment"
})
@Builder
@Getter
@Setter
public class Package {

    @JsonProperty("status")
    public String status;
    @JsonProperty("waybill")
    public String waybill;
    @JsonProperty("serviceable")
    public boolean serviceable;
    @JsonProperty("refnum")
    public String refnum;
    @JsonProperty("client")
    public String client;
    @JsonProperty("remarks")
    public String remarks;
    @JsonProperty("sort_code")
    public Object sortCode;
    @JsonProperty("cod_amount")
    public float codAmount;
    @JsonProperty("payment")
    public String payment;

}
