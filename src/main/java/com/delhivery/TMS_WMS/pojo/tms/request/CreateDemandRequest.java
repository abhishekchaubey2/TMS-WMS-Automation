package com.delhivery.TMS_WMS.pojo.tms.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class CreateDemandRequest {
    
    @JsonProperty("serviceType")
    private String serviceType;
    
    @JsonProperty("routeDetails")
    private RouteDetails routeDetails;
    
    @JsonProperty("vehicleName")
    private String vehicleName;
    
    @JsonProperty("expDispatchAt")
    private Long expDispatchAt;
    
    @JsonProperty("orderIds")
    private List<String> orderIds;
    
    public CreateDemandRequest() {}
    
    public String getServiceType() { return serviceType; }
    public void setServiceType(String serviceType) { this.serviceType = serviceType; }
    
    public RouteDetails getRouteDetails() { return routeDetails; }
    public void setRouteDetails(RouteDetails routeDetails) { this.routeDetails = routeDetails; }
    
    public String getVehicleName() { return vehicleName; }
    public void setVehicleName(String vehicleName) { this.vehicleName = vehicleName; }
    
    public Long getExpDispatchAt() { return expDispatchAt; }
    public void setExpDispatchAt(Long expDispatchAt) { this.expDispatchAt = expDispatchAt; }
    
    public List<String> getOrderIds() { return orderIds; }
    public void setOrderIds(List<String> orderIds) { this.orderIds = orderIds; }
}
