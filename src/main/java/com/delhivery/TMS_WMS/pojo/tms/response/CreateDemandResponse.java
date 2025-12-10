package com.delhivery.TMS_WMS.pojo.tms.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateDemandResponse {
    
    @JsonProperty("data")
    private DemandData data;
    
    @JsonProperty("success")
    private Boolean success;
    
    public DemandData getData() { return data; }
    public void setData(DemandData data) { this.data = data; }
    
    public Boolean getSuccess() { return success; }
    public void setSuccess(Boolean success) { this.success = success; }
}
