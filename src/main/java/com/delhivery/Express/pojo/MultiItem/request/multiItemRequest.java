package com.delhivery.Express.pojo.MultiItem.request;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
//pojo for multi item body

public class multiItemRequest {
    @JsonProperty("pickup_location")
    public PickupLocation pickupLocation;
    @JsonProperty("shipments")
    public List<MultiItemShipment> shipments;
}
