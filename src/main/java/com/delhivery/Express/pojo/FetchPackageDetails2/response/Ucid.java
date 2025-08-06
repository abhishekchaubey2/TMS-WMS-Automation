package com.delhivery.Express.pojo.FetchPackageDetails2.response;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({
"is_cc_invalid",
"is_masked",
"is_valid",
"is_mobile",
"uci"
})

public class Ucid {

@JsonProperty("is_cc_invalid")
public Object isCcInvalid;
@JsonProperty("is_masked")
public Object isMasked;
@JsonProperty("is_valid")
public Object isValid;
@JsonProperty("is_mobile")
public Object isMobile;
@JsonProperty("uci")
public Object uci;

}