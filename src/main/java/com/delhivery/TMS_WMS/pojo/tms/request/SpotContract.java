package com.delhivery.TMS_WMS.pojo.tms.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SpotContract {
    
    @JsonProperty("freightCost")
    private Double freightCost;
    
    @JsonProperty("vendorId")
    private String vendorId;
    
    @JsonProperty("vendorName")
    private String vendorName;
    
    public SpotContract() {}
    
    public SpotContract(Double freightCost, String vendorId, String vendorName) {
        this.freightCost = freightCost;
        this.vendorId = vendorId;
        this.vendorName = vendorName;
    }
    
    public Double getFreightCost() {
        return freightCost;
    }
    
    public void setFreightCost(Double freightCost) {
        this.freightCost = freightCost;
    }
    
    public String getVendorId() {
        return vendorId;
    }
    
    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }
    
    public String getVendorName() {
        return vendorName;
    }
    
    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }
}

