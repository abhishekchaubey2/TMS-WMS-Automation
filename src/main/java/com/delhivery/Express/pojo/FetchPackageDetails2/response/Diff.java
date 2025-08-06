package com.delhivery.Express.pojo.FetchPackageDetails2.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({
"dfbi",
"dfpd",
"dipd",
"def",
"dpf"
})

public class Diff {

@JsonProperty("dfbi")
public Object dfbi;
@JsonProperty("dfpd")
public Object dfpd;
@JsonProperty("dipd")
public Object dipd;
@JsonProperty("def")
public Object def;
@JsonProperty("dpf")
public Object dpf;

}
