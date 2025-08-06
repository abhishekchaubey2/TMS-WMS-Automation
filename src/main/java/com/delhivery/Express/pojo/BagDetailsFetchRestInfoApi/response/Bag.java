
package com.delhivery.Express.pojo.BagDetailsFetchRestInfoApi.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class Bag {

    @JsonProperty("heavy")
    public long heavy;
    @JsonProperty("vb")
    public Object vb;
    @JsonProperty("cn")
    public String cn;
    @JsonProperty("cm")
    public Object cm;
    @JsonProperty("ntcid")
    public Object ntcid;
    @JsonProperty("vh")
    public Object vh;
    @JsonProperty("ed")
    public String ed;
    @JsonProperty("vl")
    public Object vl;
    @JsonProperty("zn")
    public String zn;
    @JsonProperty("cd")
    public String cd;
    @JsonProperty("ar")
    public boolean ar;
    @JsonProperty("sot")
    public String sot;
    @JsonProperty("cs")
    public Cs cs;
    @JsonProperty("gm")
    public Object gm;
    @JsonProperty("inc")
    public Inc inc;
    @JsonProperty("gmtot")
    public long gmtot;
    @JsonProperty("vgm")
    public Object vgm;
    @JsonProperty("rs")
    public long rs;
    @JsonProperty("ocid")
    public String ocid;
    @JsonProperty("u")
    public String u;
    @JsonProperty("rgn")
    public String rgn;
    @JsonProperty("t")
    public String t;
    @JsonProperty("wbns")
    public List<String> wbns = null;
    @JsonProperty("pid")
    public Object pid;
    @JsonProperty("dd")
    public Dd dd;
    @JsonProperty("bss")
    public List<Object> bss = null;
    @JsonProperty("bv")
    public long bv;
    @JsonProperty("bw")
    public long bw;
    @JsonProperty("cnid")
    public String cnid;
    @JsonProperty("bs")
    public String bs;
    @JsonProperty("date")
    public Date date;
    @JsonProperty("ntc")
    public Object ntc;
    @JsonProperty("tenant")
    public String tenant;
    @JsonProperty("bpc")
    public long bpc;
    @JsonProperty("bpd")
    public String bpd;
    @JsonProperty("stn")
    public String stn;
    @JsonProperty("oc")
    public String oc;
    @JsonProperty("s")
    public List<S> s = null;
    @JsonProperty("flags")
    public Flags flags;
    @JsonProperty("cod")
    public long cod;
    @JsonProperty("mixed")
    public boolean mixed;
    @JsonProperty("ud")
    public String ud;
    @JsonProperty("_id")
    public String _id;
    @JsonProperty("hold")
    public boolean hold;

}
