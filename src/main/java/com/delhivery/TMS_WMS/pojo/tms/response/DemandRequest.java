package com.delhivery.TMS_WMS.pojo.tms.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DemandRequest {
    
    @JsonProperty("body")
    private DemandRequestBody body;
    
    public DemandRequestBody getBody() { return body; }
    public void setBody(DemandRequestBody body) { this.body = body; }
}
