
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
    "bags",
    "packages",
    "invalid",
    "ist"
})
@JsonIgnoreProperties(ignoreUnknown=true)
public class PackageDetails {

    @JsonProperty("bags")
    public List<Object> bags = null;
    @JsonProperty("packages")
    public List<Package> packages = null;
    @JsonProperty("invalid")
    public List<Object> invalid = null;
    @JsonProperty("ist")
    public List<Object> ist = null;

}
