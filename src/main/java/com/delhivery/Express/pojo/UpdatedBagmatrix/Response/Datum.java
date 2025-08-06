
package com.delhivery.Express.pojo.UpdatedBagmatrix.Response;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "bd",
    "cn",
    "bi",
    "mot",
    "bt",
    "pc",
    "lu",
    "sot",
    "sl",
    "sc",
    "st"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Datum {

    @JsonProperty("bd")
    public String bd;
    @JsonProperty("cn")
    public String cn;
    @JsonProperty("bi")
    public String bi;
    @JsonProperty("mot")
    public String mot;
    @JsonProperty("bt")
    public String bt;
    @JsonProperty("pc")
    public String pc;
    @JsonProperty("lu")
    public String lu;
    @JsonProperty("sot")
    public String sot;
    @JsonProperty("sl")
    public String sl;
    @JsonProperty("sc")
    public String sc;
    @JsonProperty("st")
    public List<String> st;

}
