package com.delhivery.Express.pojo.ClientUpdateNew.Response;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

public class Kc {
    @JsonProperty("gst_tin")
    public String gstTin;
    @JsonProperty("state")
    public String state;
    @JsonProperty("sez")
    public Boolean sez;
}
