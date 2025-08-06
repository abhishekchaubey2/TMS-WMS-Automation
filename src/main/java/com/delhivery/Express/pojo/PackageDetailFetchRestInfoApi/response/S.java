
package com.delhivery.Express.pojo.PackageDetailFetchRestInfoApi.response;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "loc",
    "slid",
    "nsl",
    "uid",
    "ss",
    "sr",
    "cty",
    "st",
    "u",
    "sl",
    "ud",
    "sd",
    "source",
    "pupid",
    "image_path",
    "asr",
    "image_bucket",
    "dest_id",
    "pid",
    "add_info",
    "dest",
    "act",
    "vid",
    "mawb",
    "ps",
    "misroute_code",
    "can_wc",
    "lock_bypass",
    "change_custody_location",
    "runs",
    "action_source",
    "vh",
    "dwbn",
    "mpos",
    "save",
    "security_check"
})
@JsonIgnoreProperties(ignoreUnknown=true)
public class S {

    @JsonProperty("loc")
    public List<Double> loc = null;
    @JsonProperty("slid")
    public String slid;
    @JsonProperty("nsl")
    public String nsl;
    @JsonProperty("uid")
    public String uid;
    @JsonProperty("ss")
    public String ss;
    @JsonProperty("sr")
    public String sr;
    @JsonProperty("cty")
    public String cty;
    @JsonProperty("st")
    public String st;
    @JsonProperty("u")
    public String u;
    @JsonProperty("sl")
    public String sl;
    @JsonProperty("ud")
    public String ud;
    @JsonProperty("sd")
    public String sd;
    @JsonProperty("source")
    public Object source;
    @JsonProperty("pupid")
    public String pupid;
    @JsonProperty("image_path")
    public String imagePath;
    @JsonProperty("asr")
    public String asr;
    @JsonProperty("image_bucket")
    public String imageBucket;
    @JsonProperty("dest_id")
    public String destId;
    @JsonProperty("pid")
    public String pid;
    @JsonProperty("add_info")
    public Object addInfo;
    @JsonProperty("dest")
    public String dest;
    @JsonProperty("act")
    public String act;
    @JsonProperty("vid")
    public String vid;
    @JsonProperty("mawb")
    public String mawb;
    @JsonProperty("ps")
    public String ps;
    @JsonProperty("misroute_code")
    public long misrouteCode;
    @JsonProperty("can_wc")
    public String canWc;
    @JsonProperty("lock_bypass")
    public String lockBypass;
    @JsonProperty("change_custody_location")
    public String changeCustodyLocation;
    @JsonProperty("runs")
    public long runs;
    @JsonProperty("action_source")
    public String actionSource;
    @JsonProperty("vh")
    public String vh;
    @JsonProperty("dwbn")
    public String dwbn;
    @JsonProperty("mpos")
    public boolean mpos;
    @JsonProperty("save")
    public boolean save;
    @JsonProperty("security_check")
    public String securityCheck;

}
