
package com.delhivery.Express.pojo.PackageDetailFetchRestInfoApi.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "dn",
    "dto",
    "fdd",
    "rto",
    "ist",
    "t",
    "srt",
    "id",
    "dct",
    "ph",
    "atc"
})
@JsonIgnoreProperties(ignoreUnknown=true)
public class Dd {

    @JsonProperty("dn")
    public String dn;
    @JsonProperty("dto")
    public boolean dto;
    @JsonProperty("fdd")
    public String fdd;
    @JsonProperty("rto")
    public boolean rto;
    @JsonProperty("ist")
    public boolean ist;
    @JsonProperty("t")
    public Object t;
    @JsonProperty("srt")
    public boolean srt;
    @JsonProperty("id")
    public String id;
    @JsonProperty("dct")
    public long dct;
    @JsonProperty("ph")
    public String ph;
    @JsonProperty("atc")
    public long atc;

}
