
package com.delhivery.Express.pojo.LMUpdateHQShipmentApi.respone;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Status",
    "client_id",
    "Result"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LMUpdateHQShipmentResponePayload {

    @JsonProperty("Status")
    public String status;
    @JsonProperty("client_id")
    public String client_id;
    @JsonProperty("Result")
    public Result result;

}
