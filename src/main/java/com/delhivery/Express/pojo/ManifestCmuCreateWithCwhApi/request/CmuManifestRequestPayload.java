package com.delhivery.Express.pojo.ManifestCmuCreateWithCwhApi.request;

import com.delhivery.Express.pojo.CmuV2ManifestNoDataShipmentApi.request.Pickup_location;
import lombok.*;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CmuManifestRequestPayload {
    @JsonProperty("pickup_location")
    public Pickup_location pickup_location;

    @JsonProperty("shipments")
    public List<Shipment> shipments = null;
    @JsonProperty("dispatch_date")
    public String dispatchDate;

}
