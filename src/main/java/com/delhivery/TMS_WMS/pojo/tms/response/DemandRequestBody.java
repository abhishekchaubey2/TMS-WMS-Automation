package com.delhivery.TMS_WMS.pojo.tms.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DemandRequestBody {
    
    @JsonProperty("properties")
    private DemandProperties properties;
    
    @JsonProperty("uniqueCode")
    private String uniqueCode;
    
    @JsonProperty("owner")
    private String owner;
    
    public DemandProperties getProperties() { return properties; }
    public void setProperties(DemandProperties properties) { this.properties = properties; }
    
    public String getUniqueCode() { return uniqueCode; }
    public void setUniqueCode(String uniqueCode) { this.uniqueCode = uniqueCode; }
    
    public String getOwner() { return owner; }
    public void setOwner(String owner) { this.owner = owner; }
}
