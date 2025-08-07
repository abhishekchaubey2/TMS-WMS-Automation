package com.delhivery.TMS.pojo.approve_react_deact_contracts.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request payload for contract reactivation
 */
public class ReactivateContractRequestPayload {
    
    @JsonProperty("startDate")
    private long startDate;
    
    @JsonProperty("endDate")
    private long endDate;
    
    // Default constructor
    public ReactivateContractRequestPayload() {
    }
    
    // Constructor with start and end dates
    public ReactivateContractRequestPayload(long startDate, long endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    // Getters and Setters
    public long getStartDate() {
        return startDate;
    }
    
    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }
    
    public long getEndDate() {
        return endDate;
    }
    
    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }
} 