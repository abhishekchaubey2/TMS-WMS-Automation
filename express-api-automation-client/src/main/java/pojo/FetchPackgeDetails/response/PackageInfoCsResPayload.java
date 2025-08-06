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
public class PackageInfoCsResPayload {
    @JsonProperty("security_check")
    private String securityCheck;

    @JsonProperty("slid")
    private String slid;

    @JsonProperty("nsl")
    private String nsl;

    @JsonProperty("uid")
    private String uid;

    @JsonProperty("loc")
    private List<Double> loc;

    @JsonProperty("vh")
    private String vh;

    @JsonProperty("sr")
    private String sr;

    @JsonProperty("cty")
    private String cty;

    @JsonProperty("st")
    private String st;

    @JsonProperty("add_info")
    private PackageInfoCsAddInfoResPayload addInfo;

    @JsonProperty("ss")
    private String ss;

    @JsonProperty("dwbn")
    private Object dWbn;

    @JsonProperty("u")
    private String u;

    @JsonProperty("sl")
    private String sl;

    @JsonProperty("ud")
    private String ud;

    @JsonProperty("sd")
    private String sd;
}
