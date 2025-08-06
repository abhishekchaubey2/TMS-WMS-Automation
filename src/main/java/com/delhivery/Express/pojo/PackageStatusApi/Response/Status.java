package com.delhivery.Express.pojo.PackageStatusApi.Response;

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
    "Status",
    "StatusDateTime",
    "StatusType",
    "Instructions"
})

public class Status {

    @JsonProperty("Status")
    public String status;
    @JsonProperty("StatusDateTime")
    public String statusDateTime;
    @JsonProperty("StatusType")
    public String statusType;
    @JsonProperty("Instructions")
    public String instructions;

}
