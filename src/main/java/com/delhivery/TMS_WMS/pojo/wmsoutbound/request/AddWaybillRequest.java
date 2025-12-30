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
public class AddWaybillRequest {
    @JsonProperty("waybill_number")
    public String waybillNumber;
    @JsonProperty("fulfillment_center_uuid")
    public String fulfillmentCenterUuid;
    @JsonProperty("dispatch_number")
    public String dispatchNumber;
}

