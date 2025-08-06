package com.delhivery.Express.pojo.FetchPackageDetails.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({
"loc",
"slid",
"nsl",
"uid",
"ss",
"sr",
"cty",
"st",
"u",
"sl",
"ud",
"sd",
"source",
"pupid",
"asr",
"app_nm"
})

public class S {

@JsonProperty("loc")
public List<Integer> loc;
@JsonProperty("slid")
public String slid;
@JsonProperty("nsl")
public String nsl;
@JsonProperty("uid")
public String uid;
@JsonProperty("ss")
public String ss;
@JsonProperty("sr")
public String sr;
@JsonProperty("cty")
public String cty;
@JsonProperty("st")
public String st;
@JsonProperty("u")
public String u;
@JsonProperty("sl")
public String sl;
@JsonProperty("ud")
public String ud;
@JsonProperty("sd")
public String sd;
//@JsonProperty("source")
//public String source;
@JsonProperty("pupid")
public String pupid;
@JsonProperty("asr")
public String asr;
@JsonProperty("app_nm")
public String appNm;

}
