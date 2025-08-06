package com.delhivery.Express.pojo.FetchPackageDetails2.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


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
public Object slid;
@JsonProperty("nsl")
public Object nsl;
@JsonProperty("uid")
public Object uid;
@JsonProperty("ss")
public Object ss;
@JsonProperty("sr")
public Object sr;
@JsonProperty("cty")
public Object cty;
@JsonProperty("st")
public Object st;
@JsonProperty("u")
public Object u;
@JsonProperty("sl")
public Object sl;
@JsonProperty("ud")
public Object ud;
@JsonProperty("sd")
public Object sd;
//@JsonProperty("source")
//public Object source;
@JsonProperty("pupid")
public Object pupid;
@JsonProperty("asr")
public Object asr;
@JsonProperty("app_nm")
public Object appNm;

}
