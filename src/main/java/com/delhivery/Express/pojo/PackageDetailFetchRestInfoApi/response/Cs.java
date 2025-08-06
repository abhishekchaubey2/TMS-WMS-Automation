
package com.delhivery.Express.pojo.PackageDetailFetchRestInfoApi.response;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "security_check",
    "slid",
    "nsl",
    "uid",
    "loc",
    "vh",
    "sr",
    "cty",
    "st",
    "add_info",
    "ss",
    "dwbn",
    "u",
    "sl",
    "ud",
    "sd"
})
@JsonIgnoreProperties(ignoreUnknown=true)
public class Cs {

    @JsonProperty("security_check")
    public String securityCheck;
    @JsonProperty("slid")
    public String slid;
    @JsonProperty("nsl")
    public String nsl;
    @JsonProperty("uid")
    public String uid;
    @JsonProperty("loc")
    public List<Double> loc = null;
    @JsonProperty("vh")
    public String vh;
    @JsonProperty("sr")
    public String sr;
    @JsonProperty("cty")
    public String cty;
    @JsonProperty("st")
    public String st;
    @JsonProperty("add_info")
    public AddInfo addInfo;
    @JsonProperty("ss")
    public String ss;
    @JsonProperty("dwbn")
    public Object dwbn;
    @JsonProperty("u")
    public String u;
    @JsonProperty("sl")
    public String sl;
    @JsonProperty("ud")
    public String ud;
    @JsonProperty("sd")
    public String sd;

}
