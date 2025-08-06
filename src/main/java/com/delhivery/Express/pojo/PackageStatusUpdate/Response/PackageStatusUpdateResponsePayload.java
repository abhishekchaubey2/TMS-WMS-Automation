package com.delhivery.Express.pojo.PackageStatusUpdate.Response;


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
    "Status",
    "Result",
    "client_id"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PackageStatusUpdateResponsePayload {

    @JsonProperty("Status")
    public String status;
    @JsonProperty("Result")
    public Result result;
    @JsonProperty("client_id")
    public String clientId;

}
