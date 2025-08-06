
package com.delhivery.Express.pojo.BagDetailsFetchRestInfoApi.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "slid",
    "uid",
    "ss",
    "sr",
    "cty",
    "u",
    "sl",
    "ud",
    "sd",
    "ed",
    "act",
    "source"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class S {

    @JsonProperty("slid")
    public String slid;
    @JsonProperty("uid")
    public String uid;
    @JsonProperty("ss")
    public String ss;
    @JsonProperty("sr")
    public String sr;
    @JsonProperty("cty")
    public String cty;
    @JsonProperty("u")
    public String u;
    @JsonProperty("sl")
    public String sl;
    @JsonProperty("ud")
    public String ud;
    @JsonProperty("sd")
    public String sd;
    @JsonProperty("ed")
    public String ed;
    @JsonProperty("act")
    public String act;
    @JsonProperty("source")
    public Object source;

}
