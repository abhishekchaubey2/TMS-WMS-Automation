package com.delhivery.Express.pojo.FetchPackageDetails.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
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
public Float dipd;
@JsonProperty("def")
public Object def;
@JsonProperty("dpf")
public Object dpf;

}
