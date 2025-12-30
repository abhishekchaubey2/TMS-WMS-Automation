package com.delhivery.TMS_WMS.pojo.tms.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AssignSpotContractRequest {
    
    @JsonProperty("contract")
    private SpotContract contract;
    
    @JsonProperty("demand")
    private DemandReference demand;
    
    public AssignSpotContractRequest() {}
    
    public SpotContract getContract() {
        return contract;
    }
    
    public void setContract(SpotContract contract) {
        this.contract = contract;
    }
    
    public DemandReference getDemand() {
        return demand;
    }
    
    public void setDemand(DemandReference demand) {
        this.demand = demand;
    }
}

