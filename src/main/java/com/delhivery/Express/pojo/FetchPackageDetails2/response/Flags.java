package com.delhivery.Express.pojo.FetchPackageDetails2.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({

})
public class Flags {

@JsonProperty("gen_req")
public Object genReq;
@JsonProperty("otp_secr")
public Object otpSecr;
@JsonProperty("rts")
public Object rts;
@JsonProperty("esntl")
public Object esntl;
@JsonProperty("is_ivr_enabled")
public Object isIvrEnabled;
@JsonProperty("is_ewbn_req")
public Object isEwbnReq;
@JsonProperty("chk_one_scure")
public Object chkOneScure;
@JsonProperty("is_exmpt")
public Object isExmpt;
@JsonProperty("cesntl")
public Object cesntl;
@JsonProperty("is_rts")
public Object isRts;
@JsonProperty("wtvr")
public Object wtvr;
@JsonProperty("per_spcfc")
public Object perSpcfc;
@JsonProperty("srv")
public Object srv;
@JsonProperty("add_spcfc")
public Object addSpcfc;
@JsonProperty("frgl")
public Object frgl;
    @JsonProperty("verified_add")
    public Boolean verifiedAdd;


}
