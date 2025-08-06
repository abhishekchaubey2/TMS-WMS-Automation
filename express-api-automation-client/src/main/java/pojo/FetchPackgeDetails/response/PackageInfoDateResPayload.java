package pojo.FetchPackgeDetails.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PackageInfoDateResPayload {
    @JsonProperty("ivd")
    private Object ivd;

    @JsonProperty("lsd")
    private Object lsd;

    @JsonProperty("ldd")
    private Object ldd;

    @JsonProperty("ed")
    private String ed;

    @JsonProperty("cd")
    private String cd;

    @JsonProperty("fbd")
    private Object fbd;

    @JsonProperty("mnd")
    private String mnd;

    @JsonProperty("fpd")
    private Object fpd;

    @JsonProperty("adt")
    private Object adt;

    @JsonProperty("dlvdt")
    private String dlvdt;

    @JsonProperty("fadt")
    private String fadt;

    @JsonProperty("ipdd")
    private String ipdd;

    @JsonProperty("lu")
    private String lu;

    @JsonProperty("ftd")
    private Object ftd;

    @JsonProperty("pd")
    private String pd;

    @JsonProperty("ebdt")
    private Object ebdt;

    @JsonProperty("fin")
    private String fin;

    @JsonProperty("hpd")
    private Object hpd;

    @JsonProperty("dlvd")
    private String dlvd;

    @JsonProperty("ced")
    private String ced;

    @JsonProperty("ladt")
    private String ladt;

    @JsonProperty("ed1")
    private String ed1;

    @JsonProperty("ntd")
    private String ntd;

    @JsonProperty("rdt")
    private Object rdt;

    @JsonProperty("fdd")
    private String fdd;

    @JsonProperty("idt")
    private Object idt;

    @JsonProperty("od")
    private Object od;

    @JsonProperty("cpd")
    private String cpd;

    @JsonProperty("cpdd")
    private String cpdd;

    @JsonProperty("ced1")
    private String ced1;

    @JsonProperty("cit")
    private String cit;

    @JsonProperty("cl_wh")
    private PackageInfoDateClWhResPayload clWh;

    @JsonProperty("pdd")
    private String pdd;
}
