package com.delhivery.Express.pojo.InstaBaggingSealApi.response.bar;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataWithBarResPayload {
    @JsonProperty("bd")
    private String bd;

    @JsonProperty("bar")
    private String bar;

    @JsonProperty("wbns")
    private List<String> wbns;

    @JsonProperty("nsc")
    private int nsc;

    @JsonProperty("bi")
    private String bi;

    @JsonProperty("bt")
    private String bt;

    @JsonProperty("bs")
    private String bs;
}
