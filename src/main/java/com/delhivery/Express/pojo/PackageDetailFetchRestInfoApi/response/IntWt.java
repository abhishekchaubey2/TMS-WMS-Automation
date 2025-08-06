
package com.delhivery.Express.pojo.PackageDetailFetchRestInfoApi.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "wtr",
    "b",
    "wt",
    "h",
    "l",
    "iwt",
    "v",
    "div"
})
@JsonIgnoreProperties(ignoreUnknown=true)
public class IntWt {

    @JsonProperty("wtr")
    public String wtr;
    @JsonProperty("b")
    public double b;
    @JsonProperty("wt")
    public double wt;
    @JsonProperty("h")
    public double h;
    @JsonProperty("l")
    public double l;
    @JsonProperty("iwt")
    public long iwt;
    @JsonProperty("v")
    public double v;
    @JsonProperty("div")
    public long div;

}
