package com.delhivery.Express.pojo.BagCustodyScanApi.Request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "action",
    "destination",
    "location",
    "bagseals",
    "sync"
})

@Builder
@Getter
@Setter
public class BagCustodyScanRequestPayload {

    @JsonProperty("action")
    public String action;
    @JsonProperty("destination")
    public String destination;
    @JsonProperty("location")
    public String location;
    @JsonProperty("bagseals")
    public String bagseals;
    @JsonProperty("sync")
    public boolean sync;

}
