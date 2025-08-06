
package com.delhivery.Express.pojo.PackageStatusUpdate.Request;

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
    "ss",
    "nsl_code",
    "st",
    "asr",
    "add_info",
    "u",
    "sl",
    "sd"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WbnPackageStatusUpdate {

    @JsonProperty("ss")
    public String ss;
    @JsonProperty("nsl_code")
    public String nslCode;
    @JsonProperty("st")
    public String st;
    @JsonProperty("asr")
    public String asr;
    @JsonProperty("add_info")
    public AddInfo addInfo;
    @JsonProperty("u")
    public String u;
    @JsonProperty("sl")
    public String sl;
    @JsonProperty("sd")
    public String sd;

}
