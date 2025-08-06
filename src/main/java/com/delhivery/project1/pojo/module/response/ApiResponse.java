package com.delhivery.project1.pojo.module.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse {
    @JsonProperty("success")
    private Boolean success;

    @JsonProperty("message")
    private String message;

    @JsonProperty("data")
    private Data data;

    @JsonProperty("errors")
    private List<Object> errors = null;

    @JsonProperty("error")
    private String error;

    @JsonProperty("_metadata")
    private Object metadata;

    @JsonProperty("timestamp")
    private String timestamp;

    @JsonProperty("path")
    private String path;

    @JsonProperty("status")
    private int status;
}