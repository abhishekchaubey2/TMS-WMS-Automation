package com.delhivery.Express.pojo.GIApi.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Error {
    @JsonProperty("msg")
    private String error;

    @JsonProperty("message")
    private String message;

    @JsonProperty("code")
    private Integer code;
}