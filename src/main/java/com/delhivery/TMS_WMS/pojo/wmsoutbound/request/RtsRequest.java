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
public class RtsRequest {
    @JsonProperty("length")
    public double length;
    @JsonProperty("width")
    public double width;
    @JsonProperty("height")
    public double height;
    @JsonProperty("weight")
    public double weight;
    @JsonProperty("waybill_number")
    public String waybillNumber;
    @JsonProperty("fulfillment_center_uuid")
    public String fulfillmentCenterUuid;
    @JsonProperty("courier")
    public String courier;
    @JsonProperty("barcode_identifier")
    public String barcodeIdentifier;
    @JsonProperty("device_id")
    public String deviceId;
    @JsonProperty("vendor_name")
    public String vendorName;
}

