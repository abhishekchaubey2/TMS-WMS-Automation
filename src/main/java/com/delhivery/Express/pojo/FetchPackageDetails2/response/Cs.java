package com.delhivery.Express.pojo.FetchPackageDetails2.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

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
	public Object asr;
	@JsonProperty("slid")
	public Object slid;
	@JsonProperty("nsl")
	public Object nsl;
	@JsonProperty("uid")
	public Object uid;
	@JsonProperty("loc")
	public List<Integer> loc;
	@JsonProperty("ss")
	public Object ss;
	@JsonProperty("sr")
	public Object sr;
	@JsonProperty("cty")
	public Object cty;
	@JsonProperty("st")
	public Object st;
	@JsonProperty("add_info")
	public Object addInfo;
	@JsonProperty("u")
	public Object u;
	@JsonProperty("sl")
	public Object sl;
	@JsonProperty("ud")
	public Object ud;
	@JsonProperty("sd")
	public Object sd;
}
