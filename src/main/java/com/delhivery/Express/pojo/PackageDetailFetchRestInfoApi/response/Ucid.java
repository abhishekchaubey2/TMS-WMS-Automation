
package com.delhivery.Express.pojo.PackageDetailFetchRestInfoApi.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "is_cc_invalid",
    "is_masked",
    "is_valid",
    "is_mobile",
    "uci"
})
@JsonIgnoreProperties(ignoreUnknown=true)
public class Ucid {

    @JsonProperty("is_cc_invalid")
    public boolean isCcInvalid;
    @JsonProperty("is_masked")
    public boolean isMasked;
    @JsonProperty("is_valid")
    public boolean isValid;
    @JsonProperty("is_mobile")
    public boolean isMobile;
    @JsonProperty("uci")
    public String uci;

}
