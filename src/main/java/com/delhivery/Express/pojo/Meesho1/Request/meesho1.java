package com.delhivery.Express.pojo.Meesho1.Request;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;
@Builder
@Getter
@Setter

public class meesho1 {
    @JsonProperty("pickup_location")
    public PickupLocationMeesho pickupLocation;
    @JsonProperty("shipments")
    public List<ShipmentMeesho> shipments;
}
