package com.delhivery.Express.pojo.FetchPackageDetailsSecond.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({
"to",
"from"
})

public class Time {

@JsonProperty("to")
public String to;
@JsonProperty("from")
public String from;

}
