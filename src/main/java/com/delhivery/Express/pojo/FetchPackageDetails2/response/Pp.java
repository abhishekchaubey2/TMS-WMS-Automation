package com.delhivery.Express.pojo.FetchPackageDetails2.response;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
@Getter

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({
    "intl",
    "cl"
})



public class Pp {

	@JsonProperty("intl")
	public Object intl;
	@JsonProperty("cl")
	public Object cl;
}
