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
public class CreateDispatchRequest {
    @JsonProperty("client_uuid")
    public String clientUuid;
    @JsonProperty("courier")
    public String courier;
    @JsonProperty("dispatch_type")
    public String dispatchType;
    @JsonProperty("courier_type")
    public String courierType;
    @JsonProperty("waybill_count")
    public Integer waybillCount;
    @JsonProperty("fulfillment_center_uuid")
    public String fulfillmentCenterUuid;
}

