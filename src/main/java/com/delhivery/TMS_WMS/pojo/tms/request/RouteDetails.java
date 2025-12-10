package com.delhivery.TMS_WMS.pojo.tms.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class RouteDetails {
    
    @JsonProperty("originFacility")
    private String originFacility;
    
    @JsonProperty("stops")
    private List<Object> stops;
    
    @JsonProperty("destinationFacility")
    private String destinationFacility;
    
    public RouteDetails() {}
    
    public RouteDetails(String originFacility, String destinationFacility) {
        this.originFacility = originFacility;
        this.destinationFacility = destinationFacility;
        this.stops = new java.util.ArrayList<>();
    }
    
    public String getOriginFacility() { return originFacility; }
    public void setOriginFacility(String originFacility) { this.originFacility = originFacility; }
    
    public List<Object> getStops() { return stops; }
    public void setStops(List<Object> stops) { this.stops = stops; }
    
    public String getDestinationFacility() { return destinationFacility; }
    public void setDestinationFacility(String destinationFacility) { this.destinationFacility = destinationFacility; }
}
