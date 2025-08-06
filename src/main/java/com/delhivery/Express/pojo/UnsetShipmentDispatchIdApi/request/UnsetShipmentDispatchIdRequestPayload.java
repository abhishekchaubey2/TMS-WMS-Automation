
package com.delhivery.Express.pojo.UnsetShipmentDispatchIdApi.request;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "retry_count",
    "action",
    "payload"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UnsetShipmentDispatchIdRequestPayload {

    @JsonProperty("retry_count")
    public int retry_count;
    @JsonProperty("action")
    public String action;
    @JsonProperty("payload")
    public Payload payload;

}
