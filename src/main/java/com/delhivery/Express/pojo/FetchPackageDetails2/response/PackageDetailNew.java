package com.delhivery.Express.pojo.FetchPackageDetails2.response;

import java.util.List;

import com.delhivery.Express.pojo.PackageDetailFetchRestInfoApi.response.IntWt;
import com.delhivery.Express.pojo.PackageDetailFetchRestInfoApi.response.Inv;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({
"cdg",
"ldd",
"itc",
"ctin",
"ch_wt",
"itm",
"wvcid",
"gm",
"bird_flag",
"slot",
"occ",
"extra",
"ipdd",
"add",
"cl_uuid",
"fadd",
"clt",
"rcty",
"rgn",
"cnc",
"rnm",
"xraycs",
"dd",
"cpin",
"mot",
"rdpc",
"cst",
"cd",
"mps_amt",
"upl",
"sht",
"plst",
"cnt_cd",
"bpin",
"qc",
"dpc",
"wbn",
"rpin",
"em",
"zn",
"int_sv",
"dpcid",
"bcount",
"ed",
"bnm",
"odt",
"mps_vwt",
"mps_wt",
"sku",
"alt_pt_ver",
"cnt",
"radd",
"fpin",
"pupid",
"dl_long",
"xray",
"scty",
"ftd",
"fcnt_cd",
"oid",
"cgm",
"sec_ucids",
"is_so",
"ntc",
"ntd",
"tenant",
"badd",
"lock",
"ndc",
"pdt",
"rdpcid",
"cty",
"dzn",
"etc",
"s",
"od_dst",
"oc",
"ucid",
"pdd",
"cnst",
"nsl",
"cn",
"cl_mode",
"volb",
"voll",
"volh",
"_src",
"ewbn",
"sot",
"fbd",
"cs",
"diff",
"ptax",
"pp",
"st_ack",
"pt",
"prefd",
"rcn",
"rcnt_cd",
"pd",
"ip",
"sstate",
"bil_typ",
"pl",
"cat_notify",
"lp",
"ctg",
"ocid",
"xray_cs",
"flags",
"add_info",
"cnid",
"int_wt",
"spid",
"rst",
"qty",
"md",
"target",
"fcty",
"tb",
"cat",
"ud",
"ivd",
"prd",
"od",
"pin",
"pseg",
"trid",
"inv",
"pri",
"al",
"ar",
"prs",
"uan",
"fpd",
"dl_lat",
"nm",
"tax_type",
"rs",
"tin",
"pclh",
"fname",
"sid",
"fst",
"fmail",
"cod",
"cl",
"_type",
"sadd",
"ntcid",
"snm",
"rcnid",
"exmpt_exp",
"inv_url",
"date",
"aseg",
"invoiceable",
"cwh_uuid",
"gst",
"znd",
"fcnt",
"cpdd",
"st",
"cod_inst",
"si",
"secr",
"u",
"mcount",
"sc",
"bzn",
"_id",
"cpdt"
})


public class PackageDetailNew {

@JsonProperty("cdg")
public Object cdg;
@JsonProperty("ldd")
public Object ldd;
@JsonProperty("itc")
public Object itc;
@JsonProperty("ctin")
public Object ctin;
@JsonProperty("ch_wt")
public ChWt chWt;
@JsonProperty("itm")
public List<Object> itm;
@JsonProperty("wvcid")
public Object wvcid;
@JsonProperty("cns")
public Object cns;
@JsonProperty("gm")
public Object gm;
@JsonProperty("bird_flag")
public Object birdFlag;
@JsonProperty("slot")
public Slot slot;
@JsonProperty("occ")
public Object occ;
@JsonProperty("extra")
public Extra extra;
@JsonProperty("ipdd")
public Object ipdd;
@JsonProperty("add")
public List<Object> add;
@JsonProperty("cl_uuid")
public Object clUuid;
@JsonProperty("fadd")
public List<Object> fadd;
@JsonProperty("clt")
public Object clt;
@JsonProperty("rcty")
public Object rcty;
@JsonProperty("rgn")
public Object rgn;
@JsonProperty("cnc")
public Object cnc;
@JsonProperty("rnm")
public Object rnm;
@JsonProperty("xraycs")
public Object xraycs;
@JsonProperty("dd")
public Dd dd;
@JsonProperty("cpin")
public Long cpin;
@JsonProperty("mot")
public Object mot;
@JsonProperty("rdpc")
public Object rdpc;
@JsonProperty("cst")
public Object cst;
@JsonProperty("cd")
public Object cd;
@JsonProperty("mps_amt")
public Object mpsAmt;
@JsonProperty("upl")
public Object upl;
@JsonProperty("sht")
public Object sht;
@JsonProperty("plst")
public Object plst;
@JsonProperty("cnt_cd")
public Object cntCd;
@JsonProperty("bpin")
public Object bpin;
@JsonProperty("qc")
public Object qc;
@JsonProperty("dpc")
public Object dpc;
@JsonProperty("wbn")
public Object wbn;
@JsonProperty("rpin")
public Object rpin;
@JsonProperty("em")
public Object em;
@JsonProperty("zn")
public Object zn;
@JsonProperty("int_sv")
public Object intSv;
@JsonProperty("dpcid")
public Object dpcid;
@JsonProperty("bcount")
public Object bcount;
@JsonProperty("ed")
public Object ed;
@JsonProperty("bnm")
public Object bnm;
@JsonProperty("odt")
public Odt odt;
@JsonProperty("mps_vwt")
public Object mpsVwt;
@JsonProperty("mps_wt")
public Object mpsWt;
@JsonProperty("sku")
public Object sku;
@JsonProperty("alt_pt_ver")
public Object altPtVer;
@JsonProperty("cnt")
public Object cnt;
@JsonProperty("radd")
public List<Object> radd;
@JsonProperty("fpin")
public Object fpin;
@JsonProperty("pupid")
public Object pupid;
@JsonProperty("dl_long")
public Object dlLong;
@JsonProperty("xray")
public Object xray;
@JsonProperty("scty")
public Object scty;
@JsonProperty("ftd")
public Object ftd;
@JsonProperty("fcnt_cd")
public Object fcntCd;
@JsonProperty("oid")
public Object oid;
@JsonProperty("cgm")
public Object cgm;
@JsonProperty("sec_ucids")
public List<Object> secUcids;
@JsonProperty("is_so")
public Object isSo;
@JsonProperty("ntc")
public Object ntc;
@JsonProperty("ntd")
public Object ntd;
@JsonProperty("tenant")
public Object tenant;
@JsonProperty("badd")
public Object badd;
@JsonProperty("lock")
public Object lock;
@JsonProperty("ndc")
public Object ndc;
@JsonProperty("pdt")
public Object pdt;
@JsonProperty("rdpcid")
public Object rdpcid;
@JsonProperty("cty")
public Object cty;
@JsonProperty("dzn")
public Object dzn;
@JsonProperty("etc")
public Object etc;
@JsonProperty("s")
public List<S> s;
@JsonProperty("od_dst")
public Object odDst;
@JsonProperty("oc")
public Object oc;
@JsonProperty("ucid")
public Ucid ucid;
@JsonProperty("pdd")
public Object pdd;
@JsonProperty("cnst")
public Object cnst;
@JsonProperty("nsl")
public List<Nsl> nsl;
@JsonProperty("cn")
public Object cn;
@JsonProperty("cl_mode")
public Object clMode;
@JsonProperty("volb")
public Object volb;
@JsonProperty("voll")
public Object voll;
@JsonProperty("volh")
public Object volh;
@JsonProperty("_src")
public Object src;
@JsonProperty("ewbn")
public List<Object> ewbn;
@JsonProperty("sot")
public Object sot;
@JsonProperty("fbd")
public Object fbd;
@JsonProperty("cs")
public Cs cs;
@JsonProperty("diff")
public Diff diff;
@JsonProperty("ptax")
public Object ptax;
@JsonProperty("pp")
public Pp pp;
@JsonProperty("st_ack")
public Object stAck;
@JsonProperty("pt")
public Object pt;
@JsonProperty("prefd")
public Object prefd;
@JsonProperty("rcn")
public Object rcn;
@JsonProperty("rcnt_cd")
public Object rcntCd;
@JsonProperty("pd")
public Object pd;
@JsonProperty("ip")
public Object ip;
@JsonProperty("sstate")
public Object sstate;
@JsonProperty("bil_typ")
public Object bilTyp;
@JsonProperty("pl")
public Object pl;
@JsonProperty("cat_notify")
public Object catNotify;
@JsonProperty("lp")
public Object lp;
@JsonProperty("ctg")
public Object ctg;
@JsonProperty("ocid")
public Object ocid;
@JsonProperty("xray_cs")
public Object xrayCs;
@JsonProperty("flags")
public Flags flags;
@JsonProperty("add_info")
public AddInfo__1 addInfo;
@JsonProperty("cnid")
public Object cnid;
@JsonProperty("int_wt")
public IntWt intWt;
@JsonProperty("spid")
public Object spid;
@JsonProperty("rst")
public Object rst;
@JsonProperty("qty")
public Object qty;
@JsonProperty("md")
public Object md;
@JsonProperty("target")
public Target target;
@JsonProperty("fcty")
public Object fcty;
@JsonProperty("tb")
public Object tb;
@JsonProperty("cat")
public List<Object> cat;
@JsonProperty("ud")
public Object ud;
@JsonProperty("ivd")
public Object ivd;
@JsonProperty("prd")
public Object prd;
@JsonProperty("od")
public Object od;
@JsonProperty("pin")
public Long pin;
@JsonProperty("pseg")
public Pseg pseg;
@JsonProperty("trid")
public Object trid;
@JsonProperty("inv")
public Inv inv;
@JsonProperty("pri")
public Object pri;
@JsonProperty("al")
public List<Object> al;
@JsonProperty("ar")
public Object ar;
@JsonProperty("prs")
public Object prs;
@JsonProperty("uan")
public Uan uan;
@JsonProperty("fpd")
public Object fpd;
@JsonProperty("dl_lat")
public Object dlLat;
@JsonProperty("nm")
public Object nm;
@JsonProperty("tax_type")
public Object taxType;
@JsonProperty("rs")
public Object rs;
@JsonProperty("tin")
public Object tin;
@JsonProperty("pclh")
public Object pclh;
@JsonProperty("fname")
public Object fname;
@JsonProperty("sid")
public Object sid;
@JsonProperty("fst")
public Object fst;
@JsonProperty("fmail")
public Object fmail;
@JsonProperty("cod")
public Object cod;
@JsonProperty("cl")
public Object cl;
@JsonProperty("_type")
public Object type;
@JsonProperty("sadd")
public Object sadd;
@JsonProperty("ntcid")
public Object ntcid;
@JsonProperty("snm")
public Object snm;
@JsonProperty("rcnid")
public Object rcnid;
@JsonProperty("exmpt_exp")
public Object exmptExp;
@JsonProperty("inv_url")
public Object invUrl;
@JsonProperty("date")
public Date date;
@JsonProperty("aseg")
public Aseg aseg;
@JsonProperty("invoiceable")
public Object invoiceable;
@JsonProperty("cwh_uuid")
public Object cwhUuid;
@JsonProperty("gst")
public Gst gst;
@JsonProperty("znd")
public Object znd;
@JsonProperty("fcnt")
public Object fcnt;
@JsonProperty("cpdd")
public Object cpdd;
@JsonProperty("st")
public Object st;
@JsonProperty("cod_inst")
public Object codInst;
@JsonProperty("si")
public Object si;
@JsonProperty("secr")
public Secr secr;
@JsonProperty("u")
public Object u;
@JsonProperty("mcount")
public Object mcount;
@JsonProperty("sc")
public Object sc;
@JsonProperty("bzn")
public Object bzn;
@JsonProperty("_id")
public Object id;
@JsonProperty("cpdt")
public Object cpdt;
@JsonProperty("i11l")
public Object i11l;
@JsonProperty("cn1")
public Object cn1;
@JsonProperty("rcn1")
public Object rcn1;
@JsonProperty("rcns")
public Object rcns;
@JsonProperty("raseg")
public Raseg raseg;
}
