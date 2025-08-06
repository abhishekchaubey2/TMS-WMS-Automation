package com.delhivery.Express.pojo.AutobagApi.Response;

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
    "weight_captured",
    "cn",
    "is_sdc",
    "ndc",
    "wtr",
    "mot",
    "ar",
    "wvcid",
    "bs",
    "wtvr",
    "cd",
    "warning"
})

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Datum {

    @JsonProperty("weight_captured")
    public boolean weightCaptured;
    @JsonProperty("cn")
    public String cn;
    @JsonProperty("is_sdc")
    public Object isSdc;
    @JsonProperty("ndc")
    public String ndc;
    @JsonProperty("wtr")
    public Object wtr;
    @JsonProperty("mot")
    public String mot;
    @JsonProperty("ar")
    public boolean ar;
    @JsonProperty("wvcid")
    public String wvcid;
    @JsonProperty("bs")
    public String bs;
    @JsonProperty("wtvr")
    public boolean wtvr;
    @JsonProperty("cd")
    public String cd;
    @JsonProperty("warning")
    public String warning;

}
