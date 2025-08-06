package com.delhivery.Express.pojo.ewbnCreate.response;
import com.fasterxml.jackson.annotation.JsonProperty;

//POJO for ewbn creation API response
public class ewbncreationresponse {
    @JsonProperty("success")
    public Boolean success;
    @JsonProperty("message")
    public String message;
    @JsonProperty("request_id")
    public String requestId;
    @JsonProperty("cb_enabled")
    public Boolean cbEnabled;
}
