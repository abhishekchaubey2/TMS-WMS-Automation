
package com.delhivery.Express.pojo.PackageDetailFetchRestInfoApi.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "rdt",
    "b_num",
    "rs",
    "ramt",
    "chrgs",
    "brd_rmt",
    "rtid",
    "brd_inv",
    "b_amt",
    "num",
    "tid",
    "dt",
    "b_dt",
    "rnum"
})
@JsonIgnoreProperties(ignoreUnknown=true)
public class Inv {

    @JsonProperty("rdt")
    public Object rdt;
    @JsonProperty("b_num")
    public String bNum;
    @JsonProperty("rs")
    public Object rs;
    @JsonProperty("ramt")
    public Object ramt;
    @JsonProperty("chrgs")
    public Chrgs chrgs;
    @JsonProperty("brd_rmt")
    public String brdRmt;
    @JsonProperty("rtid")
    public Object rtid;
    @JsonProperty("brd_inv")
    public String brdInv;
    @JsonProperty("b_amt")
    public double bAmt;
    @JsonProperty("num")
    public Object num;
    @JsonProperty("tid")
    public Object tid;
    @JsonProperty("dt")
    public Object dt;
    @JsonProperty("b_dt")
    public String bDt;
    @JsonProperty("rnum")
    public String rnum;

}
