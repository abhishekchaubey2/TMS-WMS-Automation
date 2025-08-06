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
public class PackageInfoInvResPayload {
    @JsonProperty("rdt")
    private Object rdt;

    @JsonProperty("b_num")
    private String bNum;

    @JsonProperty("rs")
    private Object rs;

    @JsonProperty("ramt")
    private Object rAmt;

    @JsonProperty("chrgs")
    private PackageInfoCsInvChrGsResPayload chRgs;

    @JsonProperty("brd_rmt")
    private String brdRmt;

    @JsonProperty("rtid")
    private Object rtId;

    @JsonProperty("brd_inv")
    private String brdInv;

    @JsonProperty("b_amt")
    private double bAmt;

    @JsonProperty("num")
    private Object num;

    @JsonProperty("tid")
    private Object tid;

    @JsonProperty("dt")
    private Object dt;

    @JsonProperty("b_dt")
    private String bDt;

    @JsonProperty("rnum")
    private String rNum;
}
