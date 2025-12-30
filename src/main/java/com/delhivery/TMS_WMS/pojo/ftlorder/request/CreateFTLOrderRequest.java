package com.delhivery.TMS_WMS.pojo.ftlorder.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Main Request POJO for FTL Order Creation
 */
public class CreateFTLOrderRequest {
    
    @JsonProperty("activity_name")
    private String activityName;
    
    @JsonProperty("data")
    private FTLOrderData data;
    
    public CreateFTLOrderRequest() {
    }
    
    public CreateFTLOrderRequest(String activityName, FTLOrderData data) {
        this.activityName = activityName;
        this.data = data;
    }
    
    public String getActivityName() { return activityName; }
    public void setActivityName(String activityName) { this.activityName = activityName; }
    
    public FTLOrderData getData() { return data; }
    public void setData(FTLOrderData data) { this.data = data; }
}

