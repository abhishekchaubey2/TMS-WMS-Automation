package com.delhivery.Express.pojo.FetchPackageDetails2.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({
"date",
"dt",
"code",
"slid"
})

public class Nsl {

@JsonProperty("date")
public Object date;
@JsonProperty("dt")
public List<Integer> dt;
@JsonProperty("code")
public Object code;
@JsonProperty("slid")
public Object slid;

}
