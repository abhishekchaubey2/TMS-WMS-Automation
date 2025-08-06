package com.delhivery.Express.pojo.CreateBagV4.Request;

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
    "origin",
    "wbns",
    "ed",
    "destination",
    "bi",
    "bt",
    "u",
    "bs",
    "ptlid",
    "sd",
    "device_id"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Datum {

    @JsonProperty("origin")
    public String origin;
    @JsonProperty("wbns")
    public WbnsV4 wbns;
    @JsonProperty("ed")
    public String ed;
    @JsonProperty("destination")
    public String destination;
    @JsonProperty("bi")
    public String bi;
    @JsonProperty("bt")
    public String bt;
    @JsonProperty("u")
    public String u;
    @JsonProperty("bs")
    public String bs;
    @JsonProperty("ptlid")
    public String ptlid;
    @JsonProperty("sd")
    public String sd;
    @JsonProperty("device_id")
    public String deviceId;

}
