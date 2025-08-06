package com.delhivery.Express.pojo.fetchewbn.response;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

public class Datum {
    @JsonProperty("wbn")
    public String wbn;
    @JsonProperty("ud")
    public String ud;
    @JsonProperty("davd")
    public String davd;
    @JsonProperty("cd")
    public String cd;
    @JsonProperty("ewbn")
    public String ewbn;
    @JsonProperty("ttl")
    public String ttl;
    @JsonProperty("expiry")
    public String expiry;
    @JsonProperty("dcn")
    public String dcn;
}
