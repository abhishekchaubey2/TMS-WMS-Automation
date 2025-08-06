package com.delhivery.Express.pojo.ClientUpdateApi.Request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Nagaland",
    "Delhi"
})

@Builder
@Getter
@Setter
public class GstStatesReq {

    @JsonProperty("Nagaland")
    public String nagaland;
    @JsonProperty("Delhi")
    public String delhi;

}
