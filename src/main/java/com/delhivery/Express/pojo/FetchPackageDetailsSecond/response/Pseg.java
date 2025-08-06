package com.delhivery.Express.pojo.FetchPackageDetailsSecond.response;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
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
public Boolean dg;
@JsonProperty("cat")
public String cat;
@JsonProperty("scat")
public String scat;
@JsonProperty("prohibited")
public Boolean prohibited;
@JsonProperty("ud")
public String ud;
@JsonProperty("dg_score")
public Integer dgScore;
@JsonProperty("cat_confidence")
public Integer catConfidence;
@JsonProperty("cft")
public Integer cft;
@JsonProperty("frgl")
public Boolean frgl;

}