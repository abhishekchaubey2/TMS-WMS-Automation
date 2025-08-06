package com.delhivery.Express.pojo.FetchPackageDetails.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({
"gen_req",
"otp_secr",
"rts",
"esntl",
"is_ivr_enabled",
"is_ewbn_req",
"chk_one_scure",
"is_exmpt",
"cesntl",
"is_rts",
"wtvr",
"per_spcfc",
"srv",
"add_spcfc",
"frgl"
})

public class Flags {

@JsonProperty("gen_req")
public Boolean genReq;
@JsonProperty("otp_secr")
public Boolean otpSecr;
@JsonProperty("rts")
public Object rts;
@JsonProperty("esntl")
public Boolean esntl;
@JsonProperty("is_ivr_enabled")
public Boolean isIvrEnabled;
@JsonProperty("is_ewbn_req")
public Boolean isEwbnReq;
@JsonProperty("chk_one_scure")
public Boolean chkOneScure;
@JsonProperty("is_exmpt")
public Boolean isExmpt;
@JsonProperty("cesntl")
public String cesntl;
@JsonProperty("is_rts")
public Boolean isRts;
@JsonProperty("wtvr")
public Boolean wtvr;
@JsonProperty("per_spcfc")
public Boolean perSpcfc;
@JsonProperty("srv")
public Boolean srv;
@JsonProperty("add_spcfc")
public Boolean addSpcfc;
@JsonProperty("frgl")
public Object frgl;

}
