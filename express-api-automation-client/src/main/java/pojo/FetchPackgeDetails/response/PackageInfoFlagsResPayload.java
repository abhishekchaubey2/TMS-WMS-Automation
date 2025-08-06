package pojo.FetchPackgeDetails.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PackageInfoFlagsResPayload {
    @JsonProperty("gen_req")
    private boolean genReq;

    @JsonProperty("otp_secr")
    private boolean otpSeCr;

    @JsonProperty("atmpt_atc_upd")
    private boolean atmptAtcUpd;

    @JsonProperty("dlvrd")
    private boolean dlvRd;

    @JsonProperty("rts")
    private Object rts;

    @JsonProperty("esntl")
    private boolean esnTl;

    @JsonProperty("is_ivr_enabled")
    private boolean isIvrEnabled;

    @JsonProperty("chk_one_scure")
    private boolean chkOneSecure;

    @JsonProperty("is_ewbn_req")
    private boolean isEWbnReq;

    @JsonProperty("swf")
    private boolean swf;

    @JsonProperty("is_exmpt")
    private boolean isExmPt;

    @JsonProperty("czn")
    private boolean czn;

    @JsonProperty("cesntl")
    private Object ceSnTl;

    @JsonProperty("is_rts")
    private boolean isRts;

    @JsonProperty("per_spcfc")
    private boolean perSpcFc;

    @JsonProperty("is_vol")
    private boolean isVol;

    @JsonProperty("add_spcfc")
    private boolean addSpcFc;

    @JsonProperty("frgl")
    private Object fRgl;

    @JsonProperty("is_inv")
    private boolean isInv;

    @JsonProperty("srv")
    private boolean srv;
}
