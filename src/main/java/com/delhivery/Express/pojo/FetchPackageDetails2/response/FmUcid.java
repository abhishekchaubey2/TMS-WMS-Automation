package com.delhivery.Express.pojo.FetchPackageDetails2.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;

@Getter

@JsonPropertyOrder({
    "uci"
})
@JsonIgnoreProperties(ignoreUnknown=true)
public class FmUcid {

    @JsonProperty("uci")
    public Object uci;

}
