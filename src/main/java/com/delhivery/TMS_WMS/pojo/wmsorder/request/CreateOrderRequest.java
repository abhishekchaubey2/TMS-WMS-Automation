package com.delhivery.TMS_WMS.pojo.wmsorder.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Main Request POJO for WMS Order Creation
 */
public class CreateOrderRequest {
    
    @JsonProperty("activity_name")
    private String activityName;
    
    @JsonProperty("data")
    private OrderData data;
    
    public CreateOrderRequest() {
    }
    
    public CreateOrderRequest(String activityName, OrderData data) {
        this.activityName = activityName;
        this.data = data;
    }
    
    public String getActivityName() { return activityName; }
    public void setActivityName(String activityName) { this.activityName = activityName; }
    
    public OrderData getData() { return data; }
    public void setData(OrderData data) { this.data = data; }
}
