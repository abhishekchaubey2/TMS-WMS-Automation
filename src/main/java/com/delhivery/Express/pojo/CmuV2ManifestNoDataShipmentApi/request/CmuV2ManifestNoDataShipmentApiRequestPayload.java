
package com.delhivery.Express.pojo.CmuV2ManifestNoDataShipmentApi.request;

import java.util.List;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "shipments",
    "pickup_location"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CmuV2ManifestNoDataShipmentApiRequestPayload {

    @JsonProperty("shipments")
    public List<Shipment> shipments = null;
    @JsonProperty("pickup_location")
    public Pickup_location pickup_location;

}
