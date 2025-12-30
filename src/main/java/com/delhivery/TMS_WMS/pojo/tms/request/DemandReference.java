package com.delhivery.TMS_WMS.pojo.tms.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DemandReference {
    
    @JsonProperty("id")
    private String id;
    
    public DemandReference() {}
    
    public DemandReference(String id) {
        this.id = id;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
}

