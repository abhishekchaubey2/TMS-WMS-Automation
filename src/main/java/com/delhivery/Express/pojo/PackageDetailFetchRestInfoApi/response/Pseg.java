
package com.delhivery.Express.pojo.PackageDetailFetchRestInfoApi.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "dg",
    "cat",
    "scat",
    "prohibited",
    "cat_confidence",
    "dg_score",
    "ud",
    "cft",
    "frgl"
})
@JsonIgnoreProperties(ignoreUnknown=true)
public class Pseg {

    @JsonProperty("dg")
    public boolean dg;
    @JsonProperty("cat")
    public String cat;
    @JsonProperty("scat")
    public String scat;
    @JsonProperty("prohibited")
    public boolean prohibited;
    @JsonProperty("cat_confidence")
    public double catConfidence;
    @JsonProperty("dg_score")
    public long dgScore;
    @JsonProperty("ud")
    public String ud;
    @JsonProperty("cft")
    public long cft;
    @JsonProperty("frgl")
    public boolean frgl;

}
