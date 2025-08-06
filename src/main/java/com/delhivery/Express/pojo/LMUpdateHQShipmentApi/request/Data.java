
package com.delhivery.Express.pojo.LMUpdateHQShipmentApi.request;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Waybill"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Data {

    @JsonProperty("Waybill")
    public com.delhivery.Express.pojo.LMUpdateHQShipmentApi.request.Waybill waybill;

}
