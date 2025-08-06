package com.delhivery.Express.pojo.DispatchFreeze.Response;

import com.delhivery.Express.pojo.ClientDetailsFetch.Response.AcceptedId;
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
    "data",
    "success",
    "error"
})
public class DispatchFreezeResponsePayload {

    @JsonProperty("data")
    public Data data;
    @JsonProperty("success")
    public boolean success;
    @JsonProperty("error")
    public String error;

}