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
public class PackageInfoResPayload {
    @JsonProperty("itm")
    private List<Object> itm;

    @JsonProperty("sec_rucids")
    private List<Object> secRucids;

    @JsonProperty("ldd")
    private String ldd;

    @JsonProperty("itc")
    private Object itc;

    @JsonProperty("fph")
    private List<String> fph;

    @JsonProperty("ctin")
    private String ctin;

    @JsonProperty("add_pii")
    private Object addPii;

    @JsonProperty("ch_wt")
    private PackageInfoChWtResPayload chWt;

    @JsonProperty("cdg")
    private Object cdg;

    @JsonProperty("wvcid")
    private String wvcid;

    @JsonProperty("gm")
    private double gm;

    @JsonProperty("slot")
    private PackageInfoSlotResPayload slot;

    @JsonProperty("occ")
    private String occ;

    @JsonProperty("volb")
    private double volb;

    @JsonProperty("ipdd")
    private String ipdd;

    @JsonProperty("ruan")
    private PackageInfoRuanResPayload ruan;

    @JsonProperty("cl_uuid")
    private String clUuid;

    @JsonProperty("bil_typ")
    private String bilTyp;

    @JsonProperty("ndc")
    private String ndc;

    @JsonProperty("fadd")
    private List<Object> fAdd;

    @JsonProperty("clt")
    private String clt;

    @JsonProperty("rcty")
    private String rCty;

    @JsonProperty("mwn")
    private String mwn;

    @JsonProperty("rgn")
    private String rgn;

    @JsonProperty("ftd")
    private String ftd;

    @JsonProperty("rnm")
    private String rnm;

    @JsonProperty("dd")
    private PackageInfoDdResPayload dd;

    @JsonProperty("cpin")
    private long cPin;

    @JsonProperty("mot")
    private String mot;

    @JsonProperty("rdpc")
    private Object rDpc;

    @JsonProperty("_src")
    private String src;

    @JsonProperty("mps_amt")
    private double mpsAmt;

    @JsonProperty("upl")
    private String upl;

    @JsonProperty("sht")
    private String sht;

    @JsonProperty("plst")
    private String pLst;

    @JsonProperty("cnt_cd")
    private String cntCd;

    @JsonProperty("bpin")
    private long bPin;

    @JsonProperty("bag")
    private PackageInfoBagResPayload bag;

    @JsonProperty("qc")
    private boolean qc;

    @JsonProperty("dpc")
    private Object dpc;

    @JsonProperty("wbn")
    private String wbn;

    @JsonProperty("rpin")
    private long rPin;

    @JsonProperty("heavy")
    private long heavy;

    @JsonProperty("zn")
    private String zn;

    @JsonProperty("int_sv")
    private boolean intSv;

    @JsonProperty("dpcid")
    private Object dPcId;

    @JsonProperty("ed")
    private String ed;

    @JsonProperty("bnm")
    private String bnm;

    @JsonProperty("mps_vwt")
    private double mpsVwt;

    @JsonProperty("mps_wt")
    private double mpsWt;

    @JsonProperty("sku")
    private Object sku;

    @JsonProperty("diff")
    private PackageInfoDiffResPayload diff;

    @JsonProperty("cnt")
    private String cnt;

    @JsonProperty("bird_flag")
    private boolean birdFlag;

    @JsonProperty("radd")
    private List<String> radd;

    @JsonProperty("cns")
    private String cns;

    @JsonProperty("fpin")
    private long fPin;

    @JsonProperty("extra")
    private PackageInfoExtraResPayload extra;

    @JsonProperty("dl_long")
    private Object dlLong;

    @JsonProperty("xray")
    private boolean xray;

    @JsonProperty("xraycs")
    private boolean xRayCs;

    @JsonProperty("si")
    private String si;

    @JsonProperty("scty")
    private String sCty;

    @JsonProperty("cnc")
    private String cnc;

    @JsonProperty("fm_ucid")
    private PackageInfoFmUcIdResPayload fmUcId;

    @JsonProperty("inv_url")
    private String invUrl;

    @JsonProperty("snm")
    private String snm;

    @JsonProperty("cgm")
    private Object cgm;

    @JsonProperty("sec_ucids")
    private List<Object> secUcIds;

    @JsonProperty("is_so")
    private boolean isSo;

    @JsonProperty("ntc")
    private Object ntc;

    @JsonProperty("spred")
    private String spRed;

    @JsonProperty("ntd")
    private String ntd;

    @JsonProperty("tenant")
    private String tenant;

    @JsonProperty("lock")
    private Object lock;

    @JsonProperty("od")
    private Object od;

    @JsonProperty("rdpcid")
    private Object rDpcId;

    @JsonProperty("cty")
    private String cty;

    @JsonProperty("dzn")
    private String dzn;

    @JsonProperty("etc")
    private String etc;

    @JsonProperty("s")
    private List<PackageInfoSResPayload> s;

    @JsonProperty("ctg")
    private String ctg;

    @JsonProperty("oc")
    private String oc;

    @JsonProperty("ucid")
    private PackageInfoUcIdResPayload ucId;

    @JsonProperty("pdd")
    private String pdd;

    @JsonProperty("secr")
    private PackageInfoSeCrResPayload seCr;

    @JsonProperty("em")
    private String em;

    @JsonProperty("cnst")
    private String cnSt;

    @JsonProperty("nsl")
    private List<PackageInfoNslResPayload> nsl;

    @JsonProperty("cn")
    private String cn;

    @JsonProperty("cl_mode")
    private Object clMode;

    @JsonProperty("cl")
    private String cl;

    @JsonProperty("voll")
    private double voLL;

    @JsonProperty("prvdr")
    private String prvDr;

    @JsonProperty("volh")
    private double voLh;

    @JsonProperty("cd")
    private String cd;

    @JsonProperty("ewbn")
    private List<Object> eWbn;

    @JsonProperty("sot")
    private String sot;

    @JsonProperty("fbd")
    private String fbd;

    @JsonProperty("cs")
    private PackageInfoCsResPayload cs;

    @JsonProperty("rph")
    private List<String> rph;

    @JsonProperty("ptax")
    private String ptAx;

    @JsonProperty("u")
    private String u;

    @JsonProperty("od_dst")
    private double odDst;

    @JsonProperty("pp")
    private PackageInfoPpResPayload pp;

    @JsonProperty("st_ack")
    private String stAck;

    @JsonProperty("pt")
    private String pt;

    @JsonProperty("prefd")
    private long preFd;

    @JsonProperty("add")
    private List<String> add;

    @JsonProperty("rcn")
    private String rcn;

    @JsonProperty("rcnt_cd")
    private String rCntCd;

    @JsonProperty("invoiceable")
    private boolean invoiceAble;

    @JsonProperty("pd")
    private String pd;

    @JsonProperty("ip")
    private boolean ip;

    @JsonProperty("sstate")
    private String sState;

    @JsonProperty("ph")
    private List<String> ph;

    @JsonProperty("mcount")
    private long mCount;

    @JsonProperty("pl")
    private String pl;

    @JsonProperty("pm")
    private List<String> pm;

    @JsonProperty("lp")
    private Object lp;

    @JsonProperty("ocid")
    private String ocId;

    @JsonProperty("xray_cs")
    private boolean xrayCs;

    @JsonProperty("flags")
    private PackageInfoFlagsResPayload flags;

    @JsonProperty("cwh_uuid")
    private String cwhUuid;

    @JsonProperty("pdt_desc")
    private String pdtDesc;

    @JsonProperty("cnid")
    private String cnid;

    @JsonProperty("bcount")
    private long bCount;

    @JsonProperty("int_wt")
    private PackageInfoIntWtResPayload intWt;

    @JsonProperty("spid")
    private Object spId;

    @JsonProperty("fcnt_cd")
    private String fCntCd;

    @JsonProperty("rst")
    private String rst;

    @JsonProperty("qty")
    private String qty;

    @JsonProperty("md")
    private String md;

    @JsonProperty("fcty")
    private String fcTy;

    @JsonProperty("tb")
    private boolean tb;

    @JsonProperty("cat")
    private List<Object> cat;

    @JsonProperty("ud")
    private String ud;

    @JsonProperty("ivd")
    private String ivd;

    @JsonProperty("prd")
    private String prd;

    @JsonProperty("pdt")
    private String pdt;

    @JsonProperty("rucid")
    private PackageInfoRucIdResPayload rucid;

    @JsonProperty("pin")
    private long pin;

    @JsonProperty("pseg")
    private PackageInfoPSegResPayload pseg;

    @JsonProperty("trid")
    private Object trId;

    @JsonProperty("inv")
    private PackageInfoInvResPayload inv;

    @JsonProperty("pid")
    private Object pid;

    @JsonProperty("al")
    private List<Object> al;

    @JsonProperty("ar")
    private boolean ar;

    @JsonProperty("prs")
    private String prs;

    @JsonProperty("uan")
    private PackageInfoUanResPayload uan;

    @JsonProperty("alt_pt_ver")
    private String altPtVer;

    @JsonProperty("bird_value")
    private long birdValue;

    @JsonProperty("fpd")
    private String fpd;

    @JsonProperty("dl_lat")
    private Object dlLat;

    @JsonProperty("nm")
    private String nm;

    @JsonProperty("tax_type")
    private String taxType;

    @JsonProperty("rs")
    private double rs;

    @JsonProperty("tin")
    private String tin;

    @JsonProperty("pclh")
    private Object pClh;

    @JsonProperty("fname")
    private Object fName;

    @JsonProperty("sid")
    private String sid;

    @JsonProperty("cst")
    private String cst;

    @JsonProperty("fmail")
    private Object fMail;

    @JsonProperty("cod")
    private double cod;

    @JsonProperty("_type")
    private String type;

    @JsonProperty("sadd")
    private String sAdd;

    @JsonProperty("dws")
    private PackageInfoDwsResPayload dws;

    @JsonProperty("ntcid")
    private Object ntCid;

    @JsonProperty("pri")
    private Object pri;

    @JsonProperty("oid")
    private String oid;

    @JsonProperty("rcnid")
    private String rcnId;

    @JsonProperty("exmpt_exp")
    private boolean exmPtExp;

    @JsonProperty("date")
    private PackageInfoDateResPayload date;

    @JsonProperty("aseg")
    private PackageInfoASegResPayload aSeg;

    @JsonProperty("add_info")
    private PackageInfoAddInfoResPayload addInfo;

    @JsonProperty("gst")
    private PackageInfoGstResPayload gst;

    @JsonProperty("odt")
    private PackageInfoOdtResPayload odt;

    @JsonProperty("znd")
    private double znd;

    @JsonProperty("fcnt")
    private String fCnt;

    @JsonProperty("cpdd")
    private String cPdd;

    @JsonProperty("st")
    private String st;

    @JsonProperty("cod_inst")
    private String codInst;

    @JsonProperty("cat_notify")
    private Object catNotify;

    @JsonProperty("badd")
    private String bAdd;

    @JsonProperty("fst")
    private Object fst;

    @JsonProperty("target")
    private PackageInfoTargetResPayload target;

    @JsonProperty("crpin")
    private long crPin;

    @JsonProperty("sc")
    private String sc;

    @JsonProperty("bzn")
    private String bzn;

    @JsonProperty("_id")
    private String id;

    @JsonProperty("cpdt")
    private String cPdt;

    @JsonProperty("pupid")
    private String puPid;

    @JsonProperty("i11l")
    private String i11l;
}
