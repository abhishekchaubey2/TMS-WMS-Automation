
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
    "dcn",
    "cngstin",
    "hsncode",
    "mvmtgstin",
    "irn",
    "slgstin",
    "clgstin"
})
@JsonIgnoreProperties(ignoreUnknown=true)
public class Gst {

    @JsonProperty("dcn")
    public List<String> dcn = null;
    @JsonProperty("cngstin")
    public String cngstin;
    @JsonProperty("hsncode")
    public List<String> hsncode = null;
    @JsonProperty("mvmtgstin")
    public String mvmtgstin;
    @JsonProperty("irn")
    public String irn;
    @JsonProperty("slgstin")
    public String slgstin;
    @JsonProperty("clgstin")
    public String clgstin;

}
