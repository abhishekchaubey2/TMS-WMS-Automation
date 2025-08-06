
package com.delhivery.Express.pojo.hqTracking.response;

import com.delhivery.Express.pojo.hqTrackingError.response.HQTrackError;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Status",
    "StatusLocation",
    "StatusDateTime",
    "RecievedBy",
    "Instructions",
    "StatusType",
    "StatusCode"
})
public class Status {

    @JsonProperty("Status")
    public String status;
    @JsonProperty("StatusLocation")
    public String statusLocation;
    @JsonProperty("StatusDateTime")
    public String statusDateTime;
    @JsonProperty("RecievedBy")
    public String recievedBy;
    @JsonProperty("Instructions")
    public String instructions;
    @JsonProperty("StatusType")
    public String statusType;
    @JsonProperty("StatusCode")
    public String statusCode;

}
