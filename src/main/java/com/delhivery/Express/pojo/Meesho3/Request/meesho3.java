package com.delhivery.Express.pojo.Meesho3.Request;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Builder
@Getter
@Setter
public class meesho3 {
    @JsonProperty("shipments")
    public List<Shipment> shipments;
}
