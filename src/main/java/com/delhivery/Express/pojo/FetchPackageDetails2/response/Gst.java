package com.delhivery.Express.pojo.FetchPackageDetails2.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({
"dcn",
"cngstin",
"hsncode",
"mvmtgstin",
"irn",
"slgstin",
"clgstin"
})

public class Gst {

@JsonProperty("dcn")
public List<Object> dcn;
@JsonProperty("cngstin")
public Object cngstin;
@JsonProperty("hsncode")
public List<Object> hsncode;
@JsonProperty("mvmtgstin")
public Object mvmtgstin;
@JsonProperty("irn")
public Object irn;
@JsonProperty("slgstin")
public Object slgstin;
@JsonProperty("clgstin")
public Object clgstin;

}
