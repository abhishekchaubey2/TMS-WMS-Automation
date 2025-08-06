package com.delhivery.Express.pojo.FetchPackageDetailsSecond.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "is_box",
    "rv",
    "b",
    "h",
    "image",
    "l",
    "min_th",
    "swt"
})
@JsonIgnoreProperties(ignoreUnknown=true)
public class Dws {

    @JsonProperty("is_box")
    public boolean isBox;
    @JsonProperty("rv")
    public double rv;
    @JsonProperty("b")
    public double b;
    @JsonProperty("h")
    public double h;
    @JsonProperty("image")
    public Image image;
    @JsonProperty("l")
    public double l;
    @JsonProperty("min_th")
    public boolean minTh;
    @JsonProperty("swt")
    public double swt;

}
