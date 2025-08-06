
package com.delhivery.Express.pojo.BagDetailsFetchRestInfoApi.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "slid",
    "sr",
    "uid",
    "ss",
    "ed",
    "act",
    "add_info",
    "source",
    "u",
    "sl",
    "ud",
    "sd"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class Cs {

    @JsonProperty("slid")
    public String slid;
    @JsonProperty("sr")
    public String sr;
    @JsonProperty("uid")
    public String uid;
    @JsonProperty("ss")
    public String ss;
    @JsonProperty("ed")
    public String ed;
    @JsonProperty("act")
    public String act;
    @JsonProperty("add_info")
    public Add_info add_info;
    @JsonProperty("source")
    public Object source;
    @JsonProperty("u")
    public String u;
    @JsonProperty("sl")
    public String sl;
    @JsonProperty("ud")
    public String ud;
    @JsonProperty("sd")
    public String sd;

}
