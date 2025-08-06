
package com.delhivery.Express.pojo.PackageDetailFetchRestInfoApi.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "dpf",
    "dfpd",
    "dipd",
    "def",
    "dfbi"
})
@JsonIgnoreProperties(ignoreUnknown=true)
public class Diff {

    @JsonProperty("dpf")
    public long dpf;
    @JsonProperty("dfpd")
    public long dfpd;
    @JsonProperty("dipd")
    public double dipd;
    @JsonProperty("def")
    public long def;
    @JsonProperty("dfbi")
    public double dfbi;

}
