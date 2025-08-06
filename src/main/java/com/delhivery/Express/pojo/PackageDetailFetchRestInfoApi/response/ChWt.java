
package com.delhivery.Express.pojo.PackageDetailFetchRestInfoApi.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "rv",
    "b",
    "dwt",
    "h",
    "wtr",
    "l",
    "wt",
    "div"
})
@JsonIgnoreProperties(ignoreUnknown=true)
public class ChWt {

    @JsonProperty("rv")
    public long rv;
    @JsonProperty("b")
    public long b;
    @JsonProperty("dwt")
    public long dwt;
    @JsonProperty("h")
    public long h;
    @JsonProperty("wtr")
    public String wtr;
    @JsonProperty("l")
    public long l;
    @JsonProperty("wt")
    public long wt;
    @JsonProperty("div")
    public long div;

}
