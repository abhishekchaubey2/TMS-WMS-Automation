
package com.delhivery.Express.pojo.PackageDetailFetchRestInfoApi.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "ivd",
    "lsd",
    "ldd",
    "ed",
    "cd",
    "fbd",
    "mnd",
    "fpd",
    "adt",
    "dlvdt",
    "fadt",
    "ipdd",
    "lu",
    "ftd",
    "pd",
    "ebdt",
    "fin",
    "hpd",
    "dlvd",
    "ced",
    "ladt",
    "ed1",
    "ntd",
    "rdt",
    "fdd",
    "idt",
    "od",
    "cpd",
    "cpdd",
    "ced1",
    "cit",
    "cl_wh",
    "pdd"
})
@JsonIgnoreProperties(ignoreUnknown=true)
public class Date {

    @JsonProperty("ivd")
    public Object ivd;
    @JsonProperty("lsd")
    public Object lsd;
    @JsonProperty("ldd")
    public Object ldd;
    @JsonProperty("ed")
    public String ed;
    @JsonProperty("cd")
    public String cd;
    @JsonProperty("fbd")
    public Object fbd;
    @JsonProperty("mnd")
    public String mnd;
    @JsonProperty("fpd")
    public Object fpd;
    @JsonProperty("adt")
    public Object adt;
    @JsonProperty("dlvdt")
    public String dlvdt;
    @JsonProperty("fadt")
    public String fadt;
    @JsonProperty("ipdd")
    public String ipdd;
    @JsonProperty("lu")
    public String lu;
    @JsonProperty("ftd")
    public Object ftd;
    @JsonProperty("pd")
    public String pd;
    @JsonProperty("ebdt")
    public Object ebdt;
    @JsonProperty("fin")
    public String fin;
    @JsonProperty("hpd")
    public Object hpd;
    @JsonProperty("dlvd")
    public String dlvd;
    @JsonProperty("ced")
    public String ced;
    @JsonProperty("ladt")
    public String ladt;
    @JsonProperty("ed1")
    public String ed1;
    @JsonProperty("ntd")
    public String ntd;
    @JsonProperty("rdt")
    public Object rdt;
    @JsonProperty("fdd")
    public String fdd;
    @JsonProperty("idt")
    public Object idt;
    @JsonProperty("od")
    public Object od;
    @JsonProperty("cpd")
    public String cpd;
    @JsonProperty("cpdd")
    public String cpdd;
    @JsonProperty("ced1")
    public String ced1;
    @JsonProperty("cit")
    public String cit;
    @JsonProperty("cl_wh")
    public ClWh clWh;
    @JsonProperty("pdd")
    public String pdd;

}
