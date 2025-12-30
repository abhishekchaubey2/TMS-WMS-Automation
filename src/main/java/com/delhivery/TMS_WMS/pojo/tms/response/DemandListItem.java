package com.delhivery.TMS_WMS.pojo.tms.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DemandListItem {
    
    @JsonProperty("id")
    private String id;
    
    @JsonProperty("uniqueCode")
    private String uniqueCode;
    
    @JsonProperty("originFacility")
    private String originFacility;
    
    @JsonProperty("destinationFacility")
    private String destinationFacility;
    
    @JsonProperty("serviceType")
    private String serviceType;
    
    @JsonProperty("state")
    private String state;
    
    // Getters and Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getUniqueCode() {
        return uniqueCode;
    }
    
    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode;
    }
    
    public String getOriginFacility() {
        return originFacility;
    }
    
    public void setOriginFacility(String originFacility) {
        this.originFacility = originFacility;
    }
    
    public String getDestinationFacility() {
        return destinationFacility;
    }
    
    public void setDestinationFacility(String destinationFacility) {
        this.destinationFacility = destinationFacility;
    }
    
    public String getServiceType() {
        return serviceType;
    }
    
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }
    
    public String getState() {
        return state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
}

