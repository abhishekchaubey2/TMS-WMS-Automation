package com.delhivery.Express.pojo.BagCustodyScanApi.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "BAG100817914"
})

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Failed {

    @JsonProperty("BAG100817914")
    public String bag100817914;

}
