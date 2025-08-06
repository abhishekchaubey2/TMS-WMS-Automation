
package com.delhivery.Express.pojo.PackageDetailFetchRestInfoApi.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "gen_req",
    "otp_secr",
    "atmpt_atc_upd",
    "dlvrd",
    "rts",
    "esntl",
    "is_ivr_enabled",
    "chk_one_scure",
    "is_ewbn_req",
    "swf",
    "is_exmpt",
    "czn",
    "cesntl",
    "is_rts",
    "per_spcfc",
    "is_vol",
    "add_spcfc",
    "frgl",
    "is_inv"
})
@JsonIgnoreProperties(ignoreUnknown=true)
public class Flags {

    @JsonProperty("gen_req")
    public boolean genReq;
    @JsonProperty("otp_secr")
    public boolean otpSecr;
    @JsonProperty("atmpt_atc_upd")
    public boolean atmptAtcUpd;
    @JsonProperty("dlvrd")
    public boolean dlvrd;
    @JsonProperty("rts")
    public Object rts;
    @JsonProperty("esntl")
    public boolean esntl;
    @JsonProperty("is_ivr_enabled")
    public boolean isIvrEnabled;
    @JsonProperty("chk_one_scure")
    public boolean chkOneScure;
    @JsonProperty("is_ewbn_req")
    public boolean isEwbnReq;
    @JsonProperty("swf")
    public boolean swf;
    @JsonProperty("is_exmpt")
    public boolean isExmpt;
    @JsonProperty("czn")
    public boolean czn;
    @JsonProperty("cesntl")
    public Object cesntl;
    @JsonProperty("is_rts")
    public boolean isRts;
    @JsonProperty("per_spcfc")
    public boolean perSpcfc;
    @JsonProperty("is_vol")
    public boolean isVol;
    @JsonProperty("add_spcfc")
    public boolean addSpcfc;
    @JsonProperty("frgl")
    public Object frgl;
    @JsonProperty("is_inv")
    public boolean isInv;
    @JsonProperty("srv")
    public boolean srv;

}
