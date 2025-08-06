package com.delhivery.Express.pojo.FetchPackageDetails2.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


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
public Object cedd;
@JsonProperty("edd")
public Object edd;
@JsonProperty("dst")
public Object dst;
@JsonProperty("cpdd")
public Object cpdd;
@JsonProperty("ipdd")
public Object ipdd;
@JsonProperty("nofly")
public Object nofly;
@JsonProperty("pdd")
public Object pdd;

}
