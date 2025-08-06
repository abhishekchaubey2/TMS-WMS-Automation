package com.delhivery.Express.pojo.FetchPackageDetails2.response;

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
    public Object isBox;
    @JsonProperty("rv")
    public Object rv;
    @JsonProperty("b")
    public Object b;
    @JsonProperty("h")
    public Object h;
    @JsonProperty("image")
    public Image image;
    @JsonProperty("l")
    public Object l;
    @JsonProperty("min_th")
    public Object minTh;
    @JsonProperty("swt")
    public Object swt;

}
