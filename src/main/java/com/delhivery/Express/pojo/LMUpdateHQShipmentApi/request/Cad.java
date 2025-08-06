
package com.delhivery.Express.pojo.LMUpdateHQShipmentApi.request;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "lat",
    "long"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cad {

    @JsonProperty("lat")
    public float lat;
    @JsonProperty("long")
    public float _long;

}
