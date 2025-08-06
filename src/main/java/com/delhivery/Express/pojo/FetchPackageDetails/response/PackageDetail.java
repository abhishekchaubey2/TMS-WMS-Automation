package com.delhivery.Express.pojo.FetchPackageDetails.response;

import java.util.List;

import com.delhivery.Express.pojo.PackageDetailFetchRestInfoApi.response.IntWt;
import com.delhivery.Express.pojo.PackageDetailFetchRestInfoApi.response.Inv;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)

@Getter
public class PackageDetail {
@JsonProperty("cdg")
public Object cdg;
@JsonProperty("ldd")
public Object ldd;
@JsonProperty("itc")
public Object itc;
@JsonProperty("ctin")
public String ctin;
@JsonProperty("ch_wt")
public ChWt chWt;
@JsonProperty("itm")
public List<Object> itm;
@JsonProperty("wvcid")
public String wvcid;
@JsonProperty("cns")
public String cns;
@JsonProperty("gm")
public Integer gm;
@JsonProperty("bird_flag")
public Integer birdFlag;
@JsonProperty("slot")
public Slot slot;
@JsonProperty("occ")
public String occ;
@JsonProperty("extra")
public Extra extra;
@JsonProperty("ipdd")
public String ipdd;
@JsonProperty("add_pii")
public String add_pii;
@JsonProperty("cl_uuid")
public String clUuid;
@JsonProperty("fadd")
public List<Object> fadd;
@JsonProperty("clt")
public String clt;
@JsonProperty("rcty")
public String rcty;
@JsonProperty("rgn")
public String rgn;
@JsonProperty("cnc")
public String cnc;
@JsonProperty("rnm")
public String rnm;
@JsonProperty("xraycs")
public Boolean xraycs;
@JsonProperty("dd")
public Dd dd;
@JsonProperty("cpin")
public Long cpin;
@JsonProperty("mot")
public String mot;
@JsonProperty("rdpc")
public Object rdpc;
@JsonProperty("cst")
public String cst;
@JsonProperty("cd")
public String cd;
@JsonProperty("mps_amt")
public Integer mpsAmt;
@JsonProperty("upl")
public String upl;
@JsonProperty("sht")
public String sht;
@JsonProperty("plst")
public String plst;
@JsonProperty("cnt_cd")
public String cntCd;
@JsonProperty("bpin")
public Integer bpin;
@JsonProperty("qc")
public Boolean qc;
@JsonProperty("dpc")
public Object dpc;
@JsonProperty("wbn")
public String wbn;
@JsonProperty("rpin")
public Integer rpin;
@JsonProperty("em")
public String em;
@JsonProperty("zn")
public String zn;
@JsonProperty("int_sv")
public Boolean intSv;
@JsonProperty("dpcid")
public Object dpcid;
@JsonProperty("bcount")
public Integer bcount;
@JsonProperty("ed")
public String ed;
@JsonProperty("bnm")
public String bnm;
@JsonProperty("odt")
public Odt odt;
@JsonProperty("mps_vwt")
public Integer mpsVwt;
@JsonProperty("mps_wt")
public Integer mpsWt;
@JsonProperty("sku")
public Object sku;
@JsonProperty("alt_pt_ver")
public String altPtVer;
@JsonProperty("cnt")
public String cnt;
@JsonProperty("radd")
public List<String> radd;
@JsonProperty("fpin")
public Integer fpin;
@JsonProperty("pupid")
public String pupid;
@JsonProperty("dl_long")
public Object dlLong;
@JsonProperty("xray")
public Boolean xray;
@JsonProperty("scty")
public String scty;
@JsonProperty("ftd")
public String ftd;
@JsonProperty("fcnt_cd")
public String fcntCd;
@JsonProperty("oid")
public String oid;
@JsonProperty("cgm")
public Object cgm;
@JsonProperty("sec_ucids")
public List<Object> secUcids;
@JsonProperty("is_so")
public Boolean isSo;
@JsonProperty("ntc")
public Object ntc;
@JsonProperty("ntd")
public String ntd;
@JsonProperty("tenant")
public String tenant;
@JsonProperty("badd")
public String badd;
@JsonProperty("lock")
public Object lock;
@JsonProperty("ndc")
public String ndc;
@JsonProperty("pdt")
public String pdt;
@JsonProperty("rdpcid")
public Object rdpcid;
@JsonProperty("cty")
public String cty;
@JsonProperty("dzn")
public String dzn;
@JsonProperty("etc")
public String etc;
@JsonProperty("s")
public List<S> s;
@JsonProperty("od_dst")
public Integer odDst;
@JsonProperty("oc")
public String oc;
@JsonProperty("ucid")
public Ucid ucid;
@JsonProperty("pdd")
public String pdd;
@JsonProperty("cnst")
public String cnst;
@JsonProperty("nsl")
public List<Nsl> nsl;
@JsonProperty("cn")
public String cn;
@JsonProperty("cl_mode")
public Object clMode;
@JsonProperty("volb")
public Integer volb;
@JsonProperty("voll")
public Integer voll;
@JsonProperty("volh")
public Integer volh;
@JsonProperty("_src")
public String src;
@JsonProperty("ewbn")
public List<Object> ewbn;
@JsonProperty("sot")
public String sot;
@JsonProperty("fbd")
public Object fbd;
@JsonProperty("cs")
public Cs cs;
@JsonProperty("diff")
public Diff diff;
@JsonProperty("ptax")
public String ptax;
@JsonProperty("pp")
public Pp pp;
@JsonProperty("st_ack")
public String stAck;
@JsonProperty("pt")
public String pt;
@JsonProperty("prefd")
public Integer prefd;
@JsonProperty("rcn")
public String rcn;
@JsonProperty("rcnt_cd")
public String rcntCd;
@JsonProperty("pd")
public String pd;
@JsonProperty("ip")
public Boolean ip;
@JsonProperty("sstate")
public String sstate;
@JsonProperty("bil_typ")
public String bilTyp;
@JsonProperty("pl")
public String pl;
@JsonProperty("cat_notify")
public Object catNotify;
@JsonProperty("lp")
public Object lp;
@JsonProperty("ctg")
public String ctg;
@JsonProperty("ocid")
public String ocid;
@JsonProperty("xray_cs")
public Boolean xrayCs;
@JsonProperty("flags")
public Flags flags;
@JsonProperty("add_info")
public AddInfo__1 addInfo;
@JsonProperty("cnid")
public String cnid;
@JsonProperty("int_wt")
public IntWt intWt;
@JsonProperty("spid")
public Object spid;
@JsonProperty("rst")
public String rst;
@JsonProperty("qty")
public String qty;
@JsonProperty("md")
public String md;
@JsonProperty("target")
public Target target;
@JsonProperty("fcty")
public Object fcty;
@JsonProperty("tb")
public Boolean tb;
@JsonProperty("cat")
public List<Object> cat;
@JsonProperty("ud")
public String ud;
@JsonProperty("ivd")
public String ivd;
@JsonProperty("prd")
public String prd;
@JsonProperty("od")
public String od;
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
public Boolean ar;
@JsonProperty("prs")
public String prs;
@JsonProperty("uan")
public Uan uan;
@JsonProperty("fpd")
public Object fpd;
@JsonProperty("dl_lat")
public Object dlLat;
@JsonProperty("nm")
public String nm;
@JsonProperty("tax_type")
public String taxType;
@JsonProperty("rs")
public Integer rs;
@JsonProperty("tin")
public String tin;
@JsonProperty("pclh")
public Object pclh;
@JsonProperty("fname")
public Object fname;
@JsonProperty("sid")
public String sid;
@JsonProperty("fst")
public Object fst;
@JsonProperty("fmail")
public Object fmail;
@JsonProperty("cod")
public Integer cod;
@JsonProperty("cl")
public String cl;
@JsonProperty("_type")
public String type;
@JsonProperty("sadd")
public String sadd;
@JsonProperty("ntcid")
public Object ntcid;
@JsonProperty("snm")
public String snm;
@JsonProperty("rcnid")
public String rcnid;
@JsonProperty("exmpt_exp")
public Boolean exmptExp;
@JsonProperty("inv_url")
public String invUrl;
@JsonProperty("date")
public Date date;
@JsonProperty("aseg")
public Aseg aseg;
@JsonProperty("invoiceable")
public Boolean invoiceable;
@JsonProperty("cwh_uuid")
public String cwhUuid;
@JsonProperty("gst")
public Gst gst;
@JsonProperty("znd")
public Integer znd;
@JsonProperty("fcnt")
public String fcnt;
@JsonProperty("cpdd")
public String cpdd;
@JsonProperty("st")
public String st;
@JsonProperty("cod_inst")
public String codInst;
@JsonProperty("si")
public String si;
@JsonProperty("secr")
public Secr secr;
@JsonProperty("u")
public String u;
@JsonProperty("mcount")
public Integer mcount;
@JsonProperty("sc")
public String sc;
@JsonProperty("bzn")
public String bzn;
@JsonProperty("_id")
public String id;
@JsonProperty("cpdt")
public String cpdt;
@JsonProperty("i11l")
public String i11l;
@JsonProperty("cn1")
public String cn1;
@JsonProperty("rcn1")
public String rcn1;
@JsonProperty("rcns")
public String rcns;
@JsonProperty("raseg")
public Raseg raseg;
@JsonProperty("einv_qr")
public Boolean einvQr;
}
