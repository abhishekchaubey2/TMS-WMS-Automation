package com.delhivery.Express.pojo.NewManifestMadatoryKeys.request;
import com.delhivery.Express.pojo.NewManifestService.request.Shipment;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter

//Pojo for mandatory keys shipment
public class MandatoryKeys {
    @JsonProperty("shipments")
    public List<Shipments> shipments;
}
