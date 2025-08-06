package com.delhivery.Express.pojo.ClientUpdateNew.Response;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

public class GstStates {
    @JsonProperty("KC")
    public Kc kc;
    @JsonProperty("QK")
    public Qk qk;
    @JsonProperty("JB")
    public Jb jb;

}

