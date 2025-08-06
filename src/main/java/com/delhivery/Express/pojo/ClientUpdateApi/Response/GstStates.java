package com.delhivery.Express.pojo.ClientUpdateApi.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "DL",
    "NL"
})

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GstStates {

    @JsonProperty("DL")
    public Dl dl;
    @JsonProperty("NL")
    public Nl nl;

}
