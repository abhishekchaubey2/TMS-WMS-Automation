package com.delhivery.TMS_WMS.pojo.tms.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DemandProperties {
    
    @JsonProperty("originFacility")
    private String originFacility;
    
    @JsonProperty("destinationFacility")
    private String destinationFacility;
    
    @JsonProperty("orderIds")
    private List<String> orderIds;
    
    @JsonProperty("serviceType")
    private String serviceType;
    
    @JsonProperty("uniqueCode")
    private String uniqueCode;
    
    public String getOriginFacility() { return originFacility; }
    public void setOriginFacility(String originFacility) { this.originFacility = originFacility; }
    
    public String getDestinationFacility() { return destinationFacility; }
    public void setDestinationFacility(String destinationFacility) { this.destinationFacility = destinationFacility; }
    
    public List<String> getOrderIds() { return orderIds; }
    public void setOrderIds(List<String> orderIds) { this.orderIds = orderIds; }
    
    public String getServiceType() { return serviceType; }
    public void setServiceType(String serviceType) { this.serviceType = serviceType; }
    
    public String getUniqueCode() { return uniqueCode; }
    public void setUniqueCode(String uniqueCode) { this.uniqueCode = uniqueCode; }
}
