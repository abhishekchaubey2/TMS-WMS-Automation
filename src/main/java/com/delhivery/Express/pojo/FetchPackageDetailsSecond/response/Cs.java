package com.delhivery.Express.pojo.FetchPackageDetailsSecond.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({
"asr",
"slid",
"nsl",
"uid",
"loc",
"ss",
"sr",
"cty",
"st",
"add_info",
"u",
"sl",
"ud",
"sd"
})

public class Cs {
	@JsonProperty("asr")
	public String asr;
	@JsonProperty("slid")
	public String slid;
	@JsonProperty("nsl")
	public String nsl;
	@JsonProperty("uid")
	public String uid;
	@JsonProperty("loc")
	public List<Integer> loc;
	@JsonProperty("ss")
	public String ss;
	@JsonProperty("sr")
	public String sr;
	@JsonProperty("cty")
	public String cty;
	@JsonProperty("st")
	public String st;
	@JsonProperty("add_info")
	public AddInfo addInfo;
	@JsonProperty("u")
	public String u;
	@JsonProperty("sl")
	public String sl;
	@JsonProperty("ud")
	public String ud;
	@JsonProperty("sd")
	public String sd;
}
