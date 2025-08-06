package com.delhivery.Express.pojo.BulkNdrEditApi.Response;

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
    "failed",
    "processed"
})

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Data {

    @JsonProperty("failed")
    public Failed failed;
    @JsonProperty("processed")
    public Processed processed;

}
