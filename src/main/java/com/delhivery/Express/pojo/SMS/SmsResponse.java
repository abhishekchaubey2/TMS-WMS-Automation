package com.delhivery.Express.pojo.SMS;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;


public class SmsResponse {
    @JsonProperty("success")
    public Boolean success;
    @JsonProperty("message")
    public List<Message> message;
}
