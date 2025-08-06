package com.delhivery.Express.pojo.ManifestMpsMasterChild.Request;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class CreateMPS {
    @JsonProperty("shipments")
    public List<ShipmentMps> shipments;
}
