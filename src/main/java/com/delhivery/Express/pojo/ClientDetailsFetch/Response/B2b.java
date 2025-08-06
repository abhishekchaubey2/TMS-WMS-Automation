package com.delhivery.Express.pojo.ClientDetailsFetch.Response;

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
    "min_wt",
    "max_wt"
})
public class B2b {

    @JsonProperty("min_wt")
    public float minWt;
    @JsonProperty("max_wt")
    public float maxWt;

}