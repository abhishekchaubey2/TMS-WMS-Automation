
package com.delhivery.Express.pojo.LMUpdateHQShipmentApi.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "wbns",
    "ss",
    "sr",
    "st",
    "add_info",
    "nsl_code",
    "u",
    "sl",
    "sd"
})
@Builder
@Getter
@Setter
public class Waybill {

    @JsonProperty("wbns")
    public List<Object> wbns = null;
    @JsonProperty("ss")
    public String ss;
    @JsonProperty("sr")
    public String sr;
    @JsonProperty("st")
    public String st;
    @JsonProperty("add_info")
    public Add_info add_info;
    @JsonProperty("nsl_code")
    public String nsl_code;
    @JsonProperty("u")
    public String u;
    @JsonProperty("sl")
    public String sl;
    @JsonProperty("sd")
    public String sd;

}
