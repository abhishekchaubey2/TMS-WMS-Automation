package com.delhivery.Express.pojo.SMS;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

public class Message {

    @JsonProperty("phone")
    public String phone;
    @JsonProperty("message")
    public String message;
    @JsonProperty("wbn")
    public String wbn;

}
