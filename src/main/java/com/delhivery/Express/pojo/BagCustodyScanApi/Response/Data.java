package com.delhivery.Express.pojo.BagCustodyScanApi.Response;

import java.util.List;
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
    "failed",
    "success"
})

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Data {

    @JsonProperty("failed")
    public Failed failed;
    @JsonProperty("success")
    public List<Object> success;

}
