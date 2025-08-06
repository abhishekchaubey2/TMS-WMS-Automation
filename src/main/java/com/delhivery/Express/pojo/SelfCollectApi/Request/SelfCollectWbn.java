
package com.delhivery.Express.pojo.SelfCollectApi.Request;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "add_info",
    "asr",
    "u",
    "wbns",
    "nsl_code",
    "ss",
    "sr",
    "sl",
    "sd",
    "st"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SelfCollectWbn {

    @JsonProperty("add_info")
    public AddInfo addInfo;
    @JsonProperty("asr")
    public String asr;
    @JsonProperty("u")
    public String u;
    @JsonProperty("wbns")
    public List<String> wbns;
    @JsonProperty("nsl_code")
    public String nslCode;
    @JsonProperty("ss")
    public String ss;
    @JsonProperty("sr")
    public String sr;
    @JsonProperty("sl")
    public String sl;
    @JsonProperty("sd")
    public String sd;
    @JsonProperty("st")
    public String st;

}
