package com.delhivery.TMS_WMS.pojo.wmsorder.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Order Data wrapper for WMS Order Creation
 */
public class OrderData {
    
    @JsonProperty("orders")
    private List<Order> orders;
    
    public OrderData() {
    }
    
    public OrderData(List<Order> orders) {
        this.orders = orders;
    }
    
    public List<Order> getOrders() { return orders; }
    public void setOrders(List<Order> orders) { this.orders = orders; }
}
