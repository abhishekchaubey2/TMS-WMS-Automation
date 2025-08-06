
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
    "date",
    "dt",
    "code",
    "slid"
})
@JsonIgnoreProperties(ignoreUnknown=true)
public class Nsl {

    @JsonProperty("date")
    public String date;
    @JsonProperty("dt")
    public List<Long> dt = null;
    @JsonProperty("code")
    public String code;
    @JsonProperty("slid")
    public String slid;

}
