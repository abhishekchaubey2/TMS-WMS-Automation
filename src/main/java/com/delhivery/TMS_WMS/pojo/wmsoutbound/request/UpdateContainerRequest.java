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
public class UpdateContainerRequest {
    @JsonProperty("client_id")
    public String clientId;
    @JsonProperty("dlv_sku")
    public String dlvSku;
    @JsonProperty("fulfillment_center")
    public String fulfillmentCenter;
    @JsonProperty("item_container")
    public String itemContainer;
    @JsonProperty("item_id")
    public Integer itemId;
    @JsonProperty("item_picked")
    public Integer itemPicked;
    @JsonProperty("movable_container")
    public String movableContainer;
    @JsonProperty("scannable_id")
    public String scannableId;
    @JsonProperty("lot_id")
    public String lotId;
    @JsonProperty("bucket")
    public String bucket;
    @JsonProperty("multi_container")
    public boolean multiContainer;
}

