package com.delhivery.Express.pojo.FetchPackageDetailsSecond.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({
"date",
"dt",
"code",
"slid"
})

public class Nsl {

@JsonProperty("date")
public String date;
@JsonProperty("dt")
public List<Integer> dt;
@JsonProperty("code")
public String code;
@JsonProperty("slid")
public String slid;

}
