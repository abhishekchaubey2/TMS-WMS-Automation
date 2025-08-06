package com.delhivery.Express.pojo.FetchPackageDetailsSecond.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
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
public Integer enUtc;
@JsonProperty("src")
public String src;
@JsonProperty("d_src")
public String dSrc;
@JsonProperty("st_utc")
public Integer stUtc;
@JsonProperty("t_src_app")
public String tSrcApp;
@JsonProperty("t_src")
public String tSrc;
@JsonProperty("time")
public List<Time> time;
@JsonProperty("date")
public String date;
@JsonProperty("src_app")
public String srcApp;
@JsonProperty("d_src_app")
public String dSrcApp;

}
