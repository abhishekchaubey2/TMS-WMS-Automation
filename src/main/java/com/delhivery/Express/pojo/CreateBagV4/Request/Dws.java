package com.delhivery.Express.pojo.CreateBagV4.Request;

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
    "rv",
    "b",
    "cuboid",
    "min_th",
    "image",
    "l",
    "wt",
    "u",
    "h",
    "sd"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dws {

    @JsonProperty("rv")
    public String rv;
    @JsonProperty("b")
    public String b;
    @JsonProperty("cuboid")
    public long cuboid;
    @JsonProperty("min_th")
    public long minTh;
    @JsonProperty("image")
    public Image image;
    @JsonProperty("l")
    public String l;
    @JsonProperty("wt")
    public String wt;
    @JsonProperty("u")
    public String u;
    @JsonProperty("h")
    public String h;
    @JsonProperty("sd")
    public String sd;

}
