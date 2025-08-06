package com.delhivery.Express.pojo.ODTat.Request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "data",
    "success",
    "error"
})
@Builder
@Getter
@Setter
public class ODTatRequestPayload {

    @JsonProperty("data")
    public DataODTat data;
    @JsonProperty("success")
    public boolean success;
    @JsonProperty("error")
    public String error;

}
