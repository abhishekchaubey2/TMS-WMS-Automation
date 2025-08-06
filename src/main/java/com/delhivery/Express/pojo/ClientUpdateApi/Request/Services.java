package com.delhivery.Express.pojo.ClientUpdateApi.Request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "REPL",
    "Pre-paid",
    "Pickup"
})

@Builder
@Getter
@Setter
public class Services {

    @JsonProperty("REPL")
    public boolean repl;
    @JsonProperty("Pre-paid")
    public boolean prePaid;
    @JsonProperty("Pickup")
    public boolean pickup;

}
