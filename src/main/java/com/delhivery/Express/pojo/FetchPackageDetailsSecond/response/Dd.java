package com.delhivery.Express.pojo.FetchPackageDetailsSecond.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({
"dto",
"atc",
"rto",
"ist",
"t",
"srt",
"dct",
"id",
"fdd"
})

public class Dd {

@JsonProperty("dto")
public Boolean dto;
@JsonProperty("atc")
public Object atc;
@JsonProperty("rto")
public Boolean rto;
@JsonProperty("ist")
public Boolean ist;
@JsonProperty("t")
public Object t;
@JsonProperty("srt")
public Boolean srt;
@JsonProperty("dct")
public Integer dct;
@JsonProperty("id")
public Object id;
@JsonProperty("fdd")
public Object fdd;

}
