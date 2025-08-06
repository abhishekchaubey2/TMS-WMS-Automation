package com.delhivery.Express.pojo.BulkNdrEditApi.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "action",
    "error_message",
    "oid"
})

@Builder
@Getter
@Setter
public class Waybill {

    @JsonProperty("action")
    public String action;
    @JsonProperty("error_message")
    public String errorMessage;
    @JsonProperty("oid")
    public String oid;

}
