package com.delhivery.Express.pojo.ApplyNslApi.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;


@Getter
public class ApplyNslResponsePayload {
    @JsonProperty("message")
    private String message;
    @JsonProperty("data")
    private String data;
    @JsonProperty("success")
    private boolean success;
}
