package com.delhivery.Express.pojo.FetchPackageDetailsSecond.response;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
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
public Boolean isCcInvalid;
@JsonProperty("is_masked")
public Boolean isMasked;
@JsonProperty("is_valid")
public Boolean isValid;
@JsonProperty("is_mobile")
public Boolean isMobile;
@JsonProperty("uci")
public String uci;

}
