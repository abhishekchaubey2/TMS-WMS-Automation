package com.delhivery.Express.pojo.FetchPackageDetails2.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({
"en_utc",
"src",
"d_src",
"st_utc",
"t_src_app",
"t_src",
"time",
"date",
"src_app",
"d_src_app"
})

public class LmFwd {

@JsonProperty("en_utc")
public Object enUtc;
@JsonProperty("src")
public Object src;
@JsonProperty("d_src")
public Object dSrc;
@JsonProperty("st_utc")
public Object stUtc;
@JsonProperty("t_src_app")
public Object tSrcApp;
@JsonProperty("t_src")
public Object tSrc;
@JsonProperty("time")
public List<Time> time;
@JsonProperty("date")
public Object date;
@JsonProperty("src_app")
public Object srcApp;
@JsonProperty("d_src_app")
public Object dSrcApp;

}