package com.delhivery.Express.pojo.BulkNdrEditApi.Request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "ndr_data"
})

@Builder
@Getter
@Setter
public class BulkNdrRequestPayload {

    @JsonProperty("ndr_data")
    public NdrData ndrData;

}
