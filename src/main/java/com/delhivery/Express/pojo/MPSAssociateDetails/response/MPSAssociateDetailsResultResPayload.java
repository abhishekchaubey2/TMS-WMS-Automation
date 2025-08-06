package com.delhivery.Express.pojo.MPSAssociateDetails.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Getter
public class MPSAssociateDetailsResultResPayload {
    @JsonProperty("prd")
    private String prd;

    @JsonProperty("zn")
    private String zn;

    @JsonProperty("cn")
    private String cn;

    @JsonProperty("pin")
    private int pin;

    @JsonProperty("cl")
    private String cl;

    @JsonProperty("intl")
    private boolean intl;

    @JsonProperty("pid")
    private String pid;

    @JsonProperty("cd")
    private String cd;

    @JsonProperty("add_pii")
    private String addPii;

    @JsonProperty("cs")
    private Map<String, String> cs;

    @JsonProperty("nm")
    private String nm;

    @JsonProperty("pt")
    private String pt;

    @JsonProperty("rs")
    private double rs;

    @JsonProperty("radd_pii")
    private String raddPii;

    @JsonProperty("occ")
    private String occ;

    @JsonProperty("sadd_pii")
    private String saddPii;

    @JsonProperty("xray")
    private boolean xray;

    @JsonProperty("rcn")
    private String rcn;

    @JsonProperty("cnc")
    private String cnc;

    @JsonProperty("pd")
    private LocalDateTime pd;

    @JsonProperty("rcty")
    private String rcty;

    @JsonProperty("mwn")
    private String mwn;

    @JsonProperty("pl")
    private String pl;

    @JsonProperty("bcount")
    private Integer bcount;

    @JsonProperty("snm")
    private String snm;

    @JsonProperty("rnm")
    private String rnm;

    @JsonProperty("oid")
    private String oid;

    @JsonProperty("cpin")
    private int cpin;

    @JsonProperty("lrn")
    private String lrn;

    @JsonProperty("wbn")
    private String wbn;

    @JsonProperty("int_wt")
    private Map<String, Integer> intWt;

    @JsonProperty("date")
    private Map<String, LocalDateTime> date;

    @JsonProperty("mps_amt")
    private int mpsAmt;

    @JsonProperty("upl")
    private String upl;

    @JsonProperty("pdd")
    private LocalDateTime pdd;

    @JsonProperty("sht")
    private String sht;

    @JsonProperty("sec_ucids")
    private String[] secUcids;

    @JsonProperty("mcount")
    private int mcount;

    @JsonProperty("od")
    private LocalDateTime od;

    @JsonProperty("flags")
    private MPSAssociateFlagsResPayload flags;

    @JsonProperty("cod")
    private int cod;

    @JsonProperty("ucid")
    private MPSAssociateUCIDResPayload ucid;

    @JsonProperty("sc")
    private String sc;

    @JsonProperty("rpin")
    private int rpin;

    @JsonProperty("pupid")
    private String pupid;

    @JsonProperty("radd")
    private List<String> radd;
}
