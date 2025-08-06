package com.delhivery.Express.pojo.CreateBagV2Api.Request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "bs",
    "destination",
    "device_id",
    "ed",
    "origin",
    "sd",
    "u",
    "bt",
    "primary",
    "wbns"
})

public class CreateBagV2ApiRequestPayload {

    @JsonProperty("bs")
    public String bs;
    @JsonProperty("destination")
    public String destination;
    @JsonProperty("device_id")
    public String deviceId;
    @JsonProperty("ed")
    public String ed;
    @JsonProperty("origin")
    public String origin;
    @JsonProperty("sd")
    public String sd;
    @JsonProperty("u")
    public String u;
    @JsonProperty("bt")
    public String bt;
    @JsonProperty("primary")
    public Primary primary;
    @JsonProperty("wbns")
    public WbnsV2 wbns;
}