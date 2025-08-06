
package com.delhivery.Express.pojo.PackageDetailFetchRestInfoApi.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "intl",
    "cl"
})
@JsonIgnoreProperties(ignoreUnknown=true)
public class Pp {

    @JsonProperty("intl")
    public long intl;
    @JsonProperty("cl")
    public boolean cl;

}
