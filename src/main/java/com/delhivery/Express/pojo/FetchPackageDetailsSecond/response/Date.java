package com.delhivery.Express.pojo.FetchPackageDetailsSecond.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({
"ivd",
"lsd",
"ced",
"ldd",
"ed",
"cd",
"ed1",
"fbd",
"mnd",
"ntd",
"od",
"fpd",
"adt",
"fdd",
"ced1",
"idt",
"fvd",
"cpd",
"cpdd",
"ipdd",
"lu",
"ftd",
"pd",
"cit",
"cl_wh",
"pdd",
"rdt",
"hpd"
})

public class Date {

@JsonProperty("ivd")
public Object ivd;
@JsonProperty("lsd")
public Object lsd;
@JsonProperty("ced")
public String ced;
@JsonProperty("ldd")
public Object ldd;
@JsonProperty("ed")
public String ed;
@JsonProperty("cd")
public String cd;
@JsonProperty("ed1")
public String ed1;
@JsonProperty("fbd")
public Object fbd;
@JsonProperty("mnd")
public String mnd;
@JsonProperty("ntd")
public String ntd;
@JsonProperty("od")
public Object od;
@JsonProperty("fpd")
public Object fpd;
@JsonProperty("adt")
public Object adt;
@JsonProperty("fdd")
public Object fdd;
@JsonProperty("ced1")
public String ced1;
@JsonProperty("idt")
public Object idt;
@JsonProperty("fvd")
public Object fvd;
@JsonProperty("cpd")
public String cpd;
@JsonProperty("cpdd")
public String cpdd;
@JsonProperty("ipdd")
public String ipdd;
@JsonProperty("lu")
public String lu;
@JsonProperty("ftd")
public Object ftd;
@JsonProperty("pd")
public String pd;
@JsonProperty("cit")
public String cit;
@JsonProperty("cl_wh")
public ClWh clWh;
@JsonProperty("pdd")
public String pdd;
@JsonProperty("rdt")
public Object rdt;
@JsonProperty("hpd")
public Object hpd;

}
