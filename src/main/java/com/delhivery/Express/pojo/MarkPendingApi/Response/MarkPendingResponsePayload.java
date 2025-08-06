package com.delhivery.Express.pojo.MarkPendingApi.Response;

import com.delhivery.Express.pojo.DispatchFreeze.Response.Data;
import com.delhivery.Express.pojo.DispatchFreeze.Response.DispatchFreezeResponsePayload;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "rmk",
    "data",
    "success"
})
public class MarkPendingResponsePayload {

    @JsonProperty("rmk")
    public String rmk;
    @JsonProperty("data")
    public Data data;
    @JsonProperty("success")
    public boolean success;

}