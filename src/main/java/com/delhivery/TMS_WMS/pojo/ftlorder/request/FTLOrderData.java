package com.delhivery.TMS_WMS.pojo.ftlorder.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * FTL Order Data wrapper for FTL Order Creation
 */
public class FTLOrderData {
    
    @JsonProperty("orders")
    private List<FTLOrder> orders;
    
    public FTLOrderData() {
    }
    
    public FTLOrderData(List<FTLOrder> orders) {
        this.orders = orders;
    }
    
    public List<FTLOrder> getOrders() { return orders; }
    public void setOrders(List<FTLOrder> orders) { this.orders = orders; }
}

