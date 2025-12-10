package com.delhivery.TMS_WMS.pojo.tms.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DemandData {
    
    @JsonProperty("request")
    private DemandRequest request;
    
    @JsonProperty("requestId")
    private String requestId;
    
    public DemandRequest getRequest() { return request; }
    public void setRequest(DemandRequest request) { this.request = request; }
    
    public String getRequestId() { return requestId; }
    public void setRequestId(String requestId) { this.requestId = requestId; }
}
