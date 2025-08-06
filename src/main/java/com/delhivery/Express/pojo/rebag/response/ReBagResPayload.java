package com.delhivery.Express.pojo.rebag.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ReBagResPayload {
    @JsonProperty("message")
    private String message;

    @JsonProperty("success")
    private Boolean success;
}
