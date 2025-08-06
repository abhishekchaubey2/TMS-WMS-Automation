package com.delhivery.Express.pojo.FetchPackageDetails2.response;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({
"dg",
"cat",
"scat",
"prohibited",
"ud",
"dg_score",
"cat_confidence",
"cft",
"frgl"
})

public class Pseg {

@JsonProperty("dg")
public Object dg;
@JsonProperty("cat")
public Object cat;
@JsonProperty("scat")
public Object scat;
@JsonProperty("prohibited")
public Object prohibited;
@JsonProperty("ud")
public Object ud;
@JsonProperty("dg_score")
public Object dgScore;
@JsonProperty("cat_confidence")
public Object catConfidence;
@JsonProperty("cft")
public Object cft;
@JsonProperty("frgl")
public Object frgl;

}