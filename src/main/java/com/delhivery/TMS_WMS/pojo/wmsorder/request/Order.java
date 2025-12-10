package com.delhivery.TMS_WMS.pojo.wmsorder.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Order POJO for WMS Order Creation
 */
public class Order {
    
    @JsonProperty("order_number")
    private String orderNumber;
    
    @JsonProperty("order_date")
    private String orderDate;
    
    @JsonProperty("order_type")
    private String orderType;
    
    @JsonProperty("channel")
    private String channel;
    
    @JsonProperty("shipments")
    private List<Shipment> shipments;
    
    @JsonProperty("consignee")
    private Consignee consignee;
    
    public Order() {
    }
    
    // Getters and Setters
    public String getOrderNumber() { return orderNumber; }
    public void setOrderNumber(String orderNumber) { this.orderNumber = orderNumber; }
    
    public String getOrderDate() { return orderDate; }
    public void setOrderDate(String orderDate) { this.orderDate = orderDate; }
    
    public String getOrderType() { return orderType; }
    public void setOrderType(String orderType) { this.orderType = orderType; }
    
    public String getChannel() { return channel; }
    public void setChannel(String channel) { this.channel = channel; }
    
    public List<Shipment> getShipments() { return shipments; }
    public void setShipments(List<Shipment> shipments) { this.shipments = shipments; }
    
    public Consignee getConsignee() { return consignee; }
    public void setConsignee(Consignee consignee) { this.consignee = consignee; }
}
