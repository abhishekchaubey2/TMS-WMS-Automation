package pojo.FetchPackgeDetails.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PackageInfoSResPayload {
    @JsonProperty("loc")
    private List<Double> loc;

    @JsonProperty("slid")
    private String slid;

    @JsonProperty("nsl")
    private String nsl;

    @JsonProperty("uid")
    private String uid;

    @JsonProperty("ss")
    private String ss;

    @JsonProperty("sr")
    private String sr;

    @JsonProperty("cty")
    private String cty;

    @JsonProperty("st")
    private String st;

    @JsonProperty("u")
    private String u;

    @JsonProperty("sl")
    private String sl;

    @JsonProperty("ud")
    private String ud;

    @JsonProperty("sd")
    private String sd;

    @JsonProperty("source")
    private Object source;

    @JsonProperty("pupid")
    private String pupId;

    @JsonProperty("image_path")
    private String imagePath;

    @JsonProperty("asr")
    private String asr;

    @JsonProperty("image_bucket")
    private String imageBucket;

    @JsonProperty("dest_id")
    private String desTId;

    @JsonProperty("pid")
    private String pid;

    @JsonProperty("add_info")
    private Object addInfo;

    @JsonProperty("dest")
    private String deSt;

    @JsonProperty("act")
    private String act;

    @JsonProperty("vid")
    private String vid;

    @JsonProperty("mawb")
    private String maWb;

    @JsonProperty("ps")
    private String ps;

    @JsonProperty("misroute_code")
    private long misRouteCode;

    @JsonProperty("can_wc")
    private String canWc;

    @JsonProperty("lock_bypass")
    private String lockBypass;

    @JsonProperty("change_custody_location")
    private String changeCustodyLocation;

    @JsonProperty("runs")
    private long runs;

    @JsonProperty("action_source")
    private String actionSource;

    @JsonProperty("vh")
    private String vh;

    @JsonProperty("dwbn")
    private String dWbn;

    @JsonProperty("mpos")
    private boolean mPos;

    @JsonProperty("save")
    private boolean save;

    @JsonProperty("security_check")
    private String securityCheck;
}
