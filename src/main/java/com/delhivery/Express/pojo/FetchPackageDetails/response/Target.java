package com.delhivery.Express.pojo.FetchPackageDetails.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({
"cedd",
"edd",
"dst",
"cpdd",
"ipdd",
"nofly",
"pdd"
})

public class Target {

@JsonProperty("cedd")
public String cedd;
@JsonProperty("edd")
public String edd;
@JsonProperty("dst")
public String dst;
@JsonProperty("cpdd")
public String cpdd;
@JsonProperty("ipdd")
public String ipdd;
@JsonProperty("nofly")
public Boolean nofly;
@JsonProperty("pdd")
public String pdd;

}
