package com.delhivery.Express.pojo.ewbnCreate.request;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder

//POJO for ewbn creation for a wbn
public class ewbncreation {
    @JsonProperty("src")
    public String src;
    @JsonProperty("callback_url")
    public String callbackUrl;
    @JsonProperty("data")
    public Dataewbn dataewbn;
    @JsonProperty("sync")
    public Boolean sync;
    @JsonProperty("awb")
    public String awb;
}
