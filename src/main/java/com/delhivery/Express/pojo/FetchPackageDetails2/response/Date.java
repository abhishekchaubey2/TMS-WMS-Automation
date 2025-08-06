package com.delhivery.Express.pojo.FetchPackageDetails2.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


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
public Object ced;
@JsonProperty("ldd")
public Object ldd;
@JsonProperty("ed")
public Object ed;
@JsonProperty("cd")
public Object cd;
@JsonProperty("ed1")
public Object ed1;
@JsonProperty("fbd")
public Object fbd;
@JsonProperty("mnd")
public Object mnd;
@JsonProperty("ntd")
public Object ntd;
@JsonProperty("od")
public Object od;
@JsonProperty("fpd")
public Object fpd;
@JsonProperty("adt")
public Object adt;
@JsonProperty("fdd")
public Object fdd;
@JsonProperty("ced1")
public Object ced1;
@JsonProperty("idt")
public Object idt;
@JsonProperty("fvd")
public Object fvd;
@JsonProperty("cpd")
public Object cpd;
@JsonProperty("cpdd")
public Object cpdd;
@JsonProperty("ipdd")
public Object ipdd;
@JsonProperty("lu")
public Object lu;
@JsonProperty("ftd")
public Object ftd;
@JsonProperty("pd")
public Object pd;
@JsonProperty("cit")
public Object cit;
@JsonProperty("cl_wh")
public ClWh clWh;
@JsonProperty("pdd")
public Object pdd;
@JsonProperty("rdt")
public Object rdt;
@JsonProperty("hpd")
public Object hpd;

}
