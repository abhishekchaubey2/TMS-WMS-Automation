package com.delhivery.TMS_WMS.pojo.tms.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TmsOrderData {
    
    @JsonProperty("orderID")
    private String orderID;
    
    public String getOrderID() {
        return orderID;
    }
    
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }
}
