
package com.delhivery.Express.pojo.GetManifestUplApi.responseFailure;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "au_sch",
    "pt",
    "cl",
    "au_dsp",
    "ocid",
    "oc",
    "mu",
    "url",
    "ext",
    "u",
    "source",
    "pd",
    "act",
    "fnm",
    "bckt",
    "pl"
})
@Builder@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class Upl {

    @JsonProperty("au_sch")
    public Object au_sch;
    @JsonProperty("pt")
    public String pt;
    @JsonProperty("cl")
    public String cl;
    @JsonProperty("au_dsp")
    public Object au_dsp;
    @JsonProperty("ocid")
    public String ocid;
    @JsonProperty("oc")
    public String oc;
    @JsonProperty("mu")
    public Object mu;
    @JsonProperty("url")
    public Object url;
    @JsonProperty("ext")
    public Object ext;
    @JsonProperty("u")
    public String u;
    @JsonProperty("source")
    public String source;
    @JsonProperty("pd")
    public String pd;
    @JsonProperty("act")
    public Object act;
    @JsonProperty("fnm")
    public Object fnm;
    @JsonProperty("bckt")
    public Object bckt;
    @JsonProperty("pl")
    public String pl;

}
