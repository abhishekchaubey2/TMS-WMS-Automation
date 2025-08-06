
package com.delhivery.Express.pojo.UnsetShipmentDispatchIdApi.request;

import java.util.List;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "dispatch_id",
    "wbns"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payload {

    @JsonProperty("dispatch_id")
    public String dispatch_id;
    @JsonProperty("wbns")
    public List<String> wbns = null;

}
