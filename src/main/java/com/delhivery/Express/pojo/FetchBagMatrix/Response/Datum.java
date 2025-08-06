
package com.delhivery.Express.pojo.FetchBagMatrix.Response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
    "bt",
    "bd",
    "sot",
    "cn",
    "sl",
    "pc",
    "st",
    "sc",
    "bi",
    "mot"
})

@Builder
@Getter
@Setter
public class Datum {

    @JsonProperty("bt")
    public String bt;
    @JsonProperty("bd")
    public String bd;
    @JsonProperty("sot")
    public String sot;
    @JsonProperty("cn")
    public String cn;
    @JsonProperty("sl")
    public String sl;
    @JsonProperty("pc")
    public String pc;
    @JsonProperty("st")
    public List<String> st;
    @JsonProperty("sc")
    public String sc;
    @JsonProperty("bi")
    public String bi;
    @JsonProperty("mot")
    public String mot;

}
