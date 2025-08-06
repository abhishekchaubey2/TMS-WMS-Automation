package com.delhivery.Express.pojo.GIApi.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GIResponsePayload {
    @JsonProperty("status")
    private int status;

    @JsonProperty("data")
    private List<Datum> data;

    @JsonProperty("error")
    private List<Error> error;
}
