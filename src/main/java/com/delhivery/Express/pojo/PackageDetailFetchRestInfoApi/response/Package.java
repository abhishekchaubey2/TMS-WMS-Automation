
package com.delhivery.Express.pojo.PackageDetailFetchRestInfoApi.response;

import java.util.List;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "itm",
    "sec_rucids",
    "ldd",
    "itc",
    "fph",
    "ctin",
    "ch_wt",
    "cdg",
    "wvcid",
    "gm",
    "slot",
    "occ",
    "volb",
    "ipdd",
    "ruan",
    "cl_uuid",
    "fadd",
    "clt",
    "rcty",
    "rgn",
    "ftd",
    "rnm",
    "dd",
    "cpin",
    "mot",
    "rdpc",
    "_src",
    "mps_amt",
    "upl",
    "sht",
    "plst",
    "cnt_cd",
    "bpin",
    "bag",
    "qc",
    "dpc",
    "wbn",
    "rpin",
    "heavy",
    "zn",
    "int_sv",
    "dpcid",
    "ed",
    "bnm",
    "mps_vwt",
    "mps_wt",
    "sku",
    "diff",
    "cnt",
    "radd",
    "cns",
    "fpin",
    "extra",
    "dl_long",
    "xray",
    "si",
    "scty",
    "cnc",
    "fm_ucid",
    "inv_url",
    "snm",
    "cgm",
    "sec_ucids",
    "is_so",
    "ntc",
    "ntd",
    "tenant",
    "lock",
    "od",
    "rdpcid",
    "cty",
    "dzn",
    "etc",
    "s",
    "ctg",
    "oc",
    "ucid",
    "pdd",
    "secr",
    "em",
    "cnst",
    "nsl",
    "cn",
    "cl_mode",
    "cl",
    "voll",
    "prvdr",
    "volh",
    "cd",
    "ewbn",
    "sot",
    "fbd",
    "cs",
    "rph",
    "ptax",
    "u",
    "od_dst",
    "pp",
    "st_ack",
    "pt",
    "prefd",
    "add",
    "rcn",
    "rcnt_cd",
    "pd",
    "ip",
    "sstate",
    "ph",
    "mcount",
    "pl",
    "pm",
    "lp",
    "ocid",
    "xray_cs",
    "flags",
    "cwh_uuid",
    "pdt_desc",
    "cnid",
    "int_wt",
    "spid",
    "fcnt_cd",
    "rst",
    "qty",
    "md",
    "fcty",
    "tb",
    "cat",
    "ud",
    "ivd",
    "prd",
    "pdt",
    "rucid",
    "pin",
    "pseg",
    "trid",
    "inv",
    "pid",
    "al",
    "ar",
    "prs",
    "uan",
    "bird_value",
    "fpd",
    "dl_lat",
    "nm",
    "tax_type",
    "rs",
    "tin",
    "pclh",
    "fname",
    "sid",
    "cst",
    "fmail",
    "cod",
    "_type",
    "sadd",
    "dws",
    "ntcid",
    "pri",
    "oid",
    "rcnid",
    "exmpt_exp",
    "date",
    "aseg",
    "add_info",
    "gst",
    "znd",
    "fcnt",
    "cpdd",
    "st",
    "cod_inst",
    "cat_notify",
    "badd",
    "fst",
    "crpin",
    "sc",
    "bzn",
    "_id",
    "cpdt",
    "pupid",
    "einv_qr"
})
@JsonIgnoreProperties(ignoreUnknown=true)
public class Package {

    @JsonProperty("itm")
    public List<Object> itm = null;
    @JsonProperty("sec_rucids")
    public List<Object> secRucids = null;
    @JsonProperty("ldd")
    public String ldd;
    @JsonProperty("itc")
    public Object itc;
    @JsonProperty("fph")
    public List<String> fph = null;
    @JsonProperty("ctin")
    public String ctin;
    @JsonProperty("ch_wt")
    public ChWt chWt;
    @JsonProperty("cdg")
    public Object cdg;
    @JsonProperty("wvcid")
    public String wvcid;
    @JsonProperty("gm")
    public double gm;
    @JsonProperty("slot")
    public Slot slot;
    @JsonProperty("occ")
    public String occ;
    @JsonProperty("volb")
    public double volb;
    @JsonProperty("ipdd")
    public String ipdd;
    @JsonProperty("ruan")
    public Ruan ruan;
    @JsonProperty("cl_uuid")
    public String clUuid;
    @JsonProperty("fadd")
    public List<Object> fadd = null;
    @JsonProperty("clt")
    public String clt;
    @JsonProperty("rcty")
    public String rcty;
    @JsonProperty("rgn")
    public String rgn;
    @JsonProperty("ftd")
    public String ftd;
    @JsonProperty("rnm")
    public String rnm;
    @JsonProperty("dd")
    public Dd dd;
    @JsonProperty("cpin")
    public long cpin;
    @JsonProperty("mot")
    public String mot;
    @JsonProperty("rdpc")
    public Object rdpc;
    @JsonProperty("_src")
    public String src;
    @JsonProperty("mps_amt")
    public double mpsAmt;
    @JsonProperty("upl")
    public String upl;
    @JsonProperty("sht")
    public String sht;
    @JsonProperty("plst")
    public String plst;
    @JsonProperty("cnt_cd")
    public String cntCd;
    @JsonProperty("bpin")
    public long bpin;
    @JsonProperty("bag")
    public Bag bag;
    @JsonProperty("qc")
    public boolean qc;
    @JsonProperty("dpc")
    public Object dpc;
    @JsonProperty("wbn")
    public String wbn;
    @JsonProperty("rpin")
    public long rpin;
    @JsonProperty("heavy")
    public long heavy;
    @JsonProperty("zn")
    public String zn;
    @JsonProperty("int_sv")
    public boolean intSv;
    @JsonProperty("dpcid")
    public Object dpcid;
    @JsonProperty("ed")
    public String ed;
    @JsonProperty("bnm")
    public String bnm;
    @JsonProperty("mps_vwt")
    public double mpsVwt;
    @JsonProperty("mps_wt")
    public double mpsWt;
    @JsonProperty("sku")
    public Object sku;
    @JsonProperty("diff")
    public Diff diff;
    @JsonProperty("cnt")
    public String cnt;
    @JsonProperty("radd")
    public List<String> radd = null;
    @JsonProperty("cns")
    public String cns;
    @JsonProperty("fpin")
    public long fpin;
    @JsonProperty("extra")
    public Extra extra;
    @JsonProperty("dl_long")
    public Object dlLong;
    @JsonProperty("xray")
    public boolean xray;
    @JsonProperty("si")
    public String si;
    @JsonProperty("scty")
    public String scty;
    @JsonProperty("cnc")
    public String cnc;
    @JsonProperty("fm_ucid")
    public FmUcid fmUcid;
    @JsonProperty("inv_url")
    public String invUrl;
    @JsonProperty("snm")
    public String snm;
    @JsonProperty("cgm")
    public Object cgm;
    @JsonProperty("sec_ucids")
    public List<Object> secUcids = null;
    @JsonProperty("is_so")
    public boolean isSo;
    @JsonProperty("ntc")
    public Object ntc;
    @JsonProperty("ntd")
    public String ntd;
    @JsonProperty("tenant")
    public String tenant;
    @JsonProperty("lock")
    public Object lock;
    @JsonProperty("od")
    public Object od;
    @JsonProperty("rdpcid")
    public Object rdpcid;
    @JsonProperty("cty")
    public String cty;
    @JsonProperty("dzn")
    public String dzn;
    @JsonProperty("etc")
    public String etc;
    @JsonProperty("s")
    public List<S> s = null;
    @JsonProperty("ctg")
    public String ctg;
    @JsonProperty("oc")
    public String oc;
    @JsonProperty("ucid")
    public Ucid ucid;
    @JsonProperty("pdd")
    public String pdd;
    @JsonProperty("secr")
    public Secr secr;
    @JsonProperty("em")
    public String em;
    @JsonProperty("cnst")
    public String cnst;
    @JsonProperty("nsl")
    public List<Nsl> nsl = null;
    @JsonProperty("cn")
    public String cn;
    @JsonIgnore
    @JsonProperty("cn")
    public String cn1;
    @JsonProperty("cl_mode")
    public Object clMode;
    @JsonProperty("cl")
    public String cl;
    @JsonProperty("voll")
    public double voll;
    @JsonProperty("prvdr")
    public String prvdr;
    @JsonProperty("volh")
    public double volh;
    @JsonProperty("cd")
    public String cd;
    @JsonProperty("ewbn")
    public List<Object> ewbn = null;
    @JsonProperty("sot")
    public String sot;
    @JsonProperty("fbd")
    public String fbd;
    @JsonProperty("cs")
    public Cs cs;
    @JsonProperty("rph")
    public List<String> rph = null;
    @JsonProperty("ptax")
    public String ptax;
    @JsonProperty("u")
    public String u;
    @JsonProperty("od_dst")
    public double odDst;
    @JsonProperty("pp")
    public Pp pp;
    @JsonProperty("st_ack")
    public String stAck;
    @JsonProperty("pt")
    public String pt;
    @JsonProperty("prefd")
    public long prefd;
    @JsonProperty("add")
    public List<String> add = null;
    @JsonProperty("rcn")
    public String rcn;
    @JsonProperty("rcnt_cd")
    public String rcntCd;
    @JsonProperty("pd")
    public String pd;
    @JsonProperty("ip")
    public boolean ip;
    @JsonProperty("sstate")
    public String sstate;
    @JsonProperty("ph")
    public List<String> ph = null;
    @JsonProperty("mcount")
    public long mcount;
    @JsonProperty("pl")
    public String pl;
    @JsonProperty("pm")
    public List<String> pm = null;
    @JsonProperty("lp")
    public Object lp;
    @JsonProperty("ocid")
    public String ocid;
    @JsonProperty("xray_cs")
    public boolean xrayCs;
    @JsonProperty("flags")
    public Flags flags;
    @JsonProperty("cwh_uuid")
    public String cwhUuid;
    @JsonProperty("pdt_desc")
    public String pdtDesc;
    @JsonProperty("cnid")
    public String cnid;
    @JsonProperty("int_wt")
    public IntWt intWt;
    @JsonProperty("spid")
    public Object spid;
    @JsonProperty("fcnt_cd")
    public String fcntCd;
    @JsonProperty("rst")
    public String rst;
    @JsonProperty("qty")
    public String qty;
    @JsonProperty("md")
    public String md;
    @JsonProperty("fcty")
    public String fcty;
    @JsonProperty("tb")
    public boolean tb;
    @JsonProperty("cat")
    public List<Object> cat = null;
    @JsonProperty("ud")
    public String ud;
    @JsonProperty("ivd")
    public String ivd;
    @JsonProperty("prd")
    public String prd;
    @JsonProperty("pdt")
    public String pdt;
    @JsonProperty("rucid")
    public Rucid rucid;
    @JsonProperty("pin")
    public long pin;
    @JsonProperty("pseg")
    public Pseg pseg;
    @JsonProperty("trid")
    public Object trid;
    @JsonProperty("inv")
    public Inv inv;
    @JsonProperty("pid")
    public Object pid;
    @JsonProperty("al")
    public List<Object> al = null;
    @JsonProperty("ar")
    public boolean ar;
    @JsonProperty("prs")
    public String prs;
    @JsonProperty("uan")
    public Uan uan;
    @JsonProperty("bird_value")
    public long birdValue;
    @JsonProperty("fpd")
    public String fpd;
    @JsonProperty("dl_lat")
    public Object dlLat;
    @JsonProperty("nm")
    public String nm;
    @JsonProperty("tax_type")
    public String taxType;
    @JsonProperty("rs")
    public double rs;
    @JsonProperty("tin")
    public String tin;
    @JsonProperty("pclh")
    public Object pclh;
    @JsonProperty("fname")
    public Object fname;
    @JsonProperty("sid")
    public String sid;
    @JsonProperty("cst")
    public String cst;
    @JsonProperty("fmail")
    public Object fmail;
    @JsonProperty("cod")
    public double cod;
    @JsonProperty("_type")
    public String type;
    @JsonProperty("sadd")
    public String sadd;
    @JsonProperty("dws")
    public Dws dws;
    @JsonProperty("ntcid")
    public Object ntcid;
    @JsonProperty("pri")
    public Object pri;
    @JsonProperty("oid")
    public String oid;
    @JsonProperty("rcnid")
    public String rcnid;
    @JsonProperty("exmpt_exp")
    public boolean exmptExp;
    @JsonProperty("date")
    public Date date;
    @JsonProperty("aseg")
    public Aseg aseg;
    @JsonProperty("add_info")
    public AddInfo__1 addInfo;
    @JsonProperty("gst")
    public Gst gst;
    @JsonProperty("znd")
    public double znd;
    @JsonProperty("fcnt")
    public String fcnt;
    @JsonProperty("cpdd")
    public String cpdd;
    @JsonProperty("st")
    public String st;
    @JsonProperty("cod_inst")
    public String codInst;
    @JsonProperty("cat_notify")
    public Object catNotify;
    @JsonProperty("badd")
    public String badd;
    @JsonProperty("fst")
    public Object fst;
    @JsonProperty("crpin")
    public long crpin;
    @JsonProperty("sc")
    public String sc;
    @JsonProperty("bzn")
    public String bzn;
    @JsonProperty("_id")
    public String id;
    @JsonProperty("cpdt")
    public String cpdt;
    @JsonProperty("pupid")
    public String pupid;
    @JsonProperty("i11l")
    public String i11l;
    
}
