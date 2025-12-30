package com.delhivery.TMS_WMS.pojo.ftlorder.request;

import com.delhivery.TMS_WMS.pojo.wmsorder.request.Consignee;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * FTL Order POJO for FTL Order Creation
 */
public class FTLOrder {
    
    @JsonProperty("order_number")
    private String orderNumber;
    
    @JsonProperty("order_date")
    private String orderDate;
    
    @JsonProperty("order_type")
    private String orderType;
    
    @JsonProperty("channel")
    private String channel;
    
    @JsonProperty("shipments")
    private List<FTLShipment> shipments;
    
    @JsonProperty("consignee")
    private Consignee consignee;
    
    public FTLOrder() {
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
    
    public List<FTLShipment> getShipments() { return shipments; }
    public void setShipments(List<FTLShipment> shipments) { this.shipments = shipments; }
    
    public Consignee getConsignee() { return consignee; }
    public void setConsignee(Consignee consignee) { this.consignee = consignee; }
}

