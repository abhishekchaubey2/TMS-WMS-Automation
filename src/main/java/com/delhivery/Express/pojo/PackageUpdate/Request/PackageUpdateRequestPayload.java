package com.delhivery.Express.pojo.PackageUpdate.Request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "wbn",
    "l",
    "b",
    "h",
    "wt",
    "v",
    "rv"
})
@Builder
@Getter
@Setter
public class PackageUpdateRequestPayload {

    @JsonProperty("wbn")
    public String wbn;
    @JsonProperty("l")
    public String l;
    @JsonProperty("b")
    public String b;
    @JsonProperty("h")
    public String h;
    @JsonProperty("wt")
    public String wt;
    @JsonProperty("v")
    public String v;
    @JsonProperty("rv")
    public String rv;

}
