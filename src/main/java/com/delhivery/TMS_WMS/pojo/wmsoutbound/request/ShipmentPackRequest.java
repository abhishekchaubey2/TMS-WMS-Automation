package com.delhivery.TMS_WMS.pojo.wmsoutbound.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShipmentPackRequest {
    @JsonProperty("shipment_id")
    public Integer shipmentId;
    @JsonProperty("fulfillment_center_id")
    public String fulfillmentCenterId;
    @JsonProperty("retry")
    public Boolean retry;
}

