
package com.delhivery.Express.pojo.GIApi.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Datum {
    @JsonProperty("gen_req")
    private boolean gen_req;

    @JsonProperty("prd")
    private String prd;

    @JsonProperty("slid")
    private String slid;

    @JsonProperty("pdt")
    private String pdt;

    @JsonProperty("nsl")
    private String nsl;

    @JsonProperty("cn")
    private String cn;

    @JsonProperty("pin")
    private long pin;

    @JsonProperty("cl")
    private String cl;

    @JsonProperty("intl")
    private Object intl;

    @JsonProperty("pri")
    private Object pri;

    @JsonProperty("zn")
    private String zn;

    @JsonProperty("locality_type")
    private String locality_type;

    @JsonProperty("ar")
    private Object ar;

    @JsonProperty("wvcid")
    private String wvcid;

    @JsonProperty("s_")
    private String s_;

    @JsonProperty("st")
    private String st;

    @JsonProperty("wv")
    private String wv;

    @JsonProperty("slot")
    private Object slot;

    @JsonProperty("radd")
    private String radd;

    @JsonProperty("weight_lock")
    private boolean weight_lock;

    @JsonProperty("locality")
    private String locality;

    @JsonProperty("pet")
    private Object pet;

    @JsonProperty("return_locality_type")
    private String return_locality_type;

    @JsonProperty("dst")
    private Object dst;

    @JsonProperty("xray")
    private boolean xray;

    @JsonProperty("rpc_ageing")
    private boolean rpc_ageing;

    @JsonProperty("rcn")
    private String rcn;

    @JsonProperty("flare_agent")
    private String flare_agent;

    @JsonProperty("is_rts")
    private boolean is_rts;

    @JsonProperty("det")
    private Object det;

    @JsonProperty("mcount")
    private long mcount;

    @JsonProperty("mwn")
    private Object mwn;

    @JsonProperty("pl")
    private String pl;

    @JsonProperty("wtr")
    private String wtr;

    @JsonProperty("flare_name")
    private String flare_name;

    @JsonProperty("di")
    private String di;

    @JsonProperty("xraycs")
    private boolean xraycs;

    @JsonProperty("pid")
    private Object pid;

    @JsonProperty("oid")
    private String oid;

    @JsonProperty("rcnid")
    private String rcnid;

    @JsonProperty("parent_pkey")
    private String parent_pkey;

    @JsonProperty("cnid")
    private String cnid;

    @JsonProperty("mot")
    private String mot;

    @JsonProperty("ntd")
    private Object ntd;

    @JsonProperty("pst")
    private Object pst;

    @JsonProperty("md")
    private String md;

    @JsonProperty("pdd")
    private String pdd;

    @JsonProperty("sht")
    private String sht;

    @JsonProperty("is_sdc")
    private boolean is_sdc;

    @JsonProperty("old_s")
    private String old_s;

    @JsonProperty("return_locality")
    private String return_locality;

    @JsonProperty("ndc")
    private String ndc;

    @JsonProperty("esntl")
    private boolean esntl;

    @JsonProperty("cpd")
    private String cpd;

    @JsonProperty("cat")
    private List<Object> cat = null;

    @JsonProperty("fr_typ")
    private Object fr_typ;

    @JsonProperty("s")
    private String s;

    @JsonProperty("sclt")
    private Object sclt;

    @JsonProperty("sl")
    private String sl;

    @JsonProperty("wbn")
    private String wbn;

    @JsonProperty("error")
    private String error;

    @JsonProperty("wtvr")
    private Object wtvr;

    @JsonProperty("rpin")
    private long rpin;

    @JsonProperty("current_s")
    private String current_s;

    @JsonProperty("route")
    private String route;

    @JsonProperty("hold")
    public boolean hold;
}
