package com.delhivery.Express.pojo.FetchPackageDetails2.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({
"vs",
"nm"
})

public class Odt {

@JsonProperty("vs")
public Object vs;
@JsonProperty("nm")
public Object nm;

}
