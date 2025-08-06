package com.delhivery.Express.pojo.FetchPackageDetails2.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


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
public Object dto;
@JsonProperty("atc")
public Object atc;
@JsonProperty("rto")
public Object rto;
@JsonProperty("ist")
public Object ist;
@JsonProperty("t")
public Object t;
@JsonProperty("srt")
public Object srt;
@JsonProperty("dct")
public Object dct;
@JsonProperty("id")
public Object id;
@JsonProperty("fdd")
public Object fdd;

}
