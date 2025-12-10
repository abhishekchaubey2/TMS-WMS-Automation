package com.delhivery.TMS_WMS.pojo.picklist.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PicklistItem {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("shipment_id")
    private Integer shipmentId;

    @JsonProperty("all_shipment_id")
    private List<Integer> allShipmentIds;

    @JsonProperty("del_sku")
    private String delSku;

    @JsonProperty("scannable_id")
    private String scannableId;

    @JsonProperty("location")
    private String location;

    @JsonProperty("bucket")
    private String bucket;

    @JsonProperty("remaining_qty")
    private Integer remainingQty;

    public Integer getId() {
        return id;
    }

    public Integer getShipmentId() {
        return shipmentId;
    }

    public List<Integer> getAllShipmentIds() {
        return allShipmentIds;
    }

    public String getDelSku() {
        return delSku;
    }

    public String getScannableId() {
        return scannableId;
    }

    public String getLocation() {
        return location;
    }

    public String getBucket() {
        return bucket;
    }

    public Integer getRemainingQty() {
        return remainingQty;
    }
}


