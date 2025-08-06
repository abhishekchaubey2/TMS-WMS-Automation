
package com.delhivery.Express.pojo.TripIncomingApi.request;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "trid",
    "slid"
})
@Builder
@Getter
@Setter
public class TripIncomingRequestPayload {

    @JsonProperty("trid")
    public String trid;
    @JsonProperty("slid")
    public String slid;

}
