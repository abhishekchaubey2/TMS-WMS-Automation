package com.delhivery.TMS_WMS.pojo.tms.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Request POJO for creating indent (bulk indent API)
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateIndentRequest {
    
    @JsonProperty("loadIds")
    private List<String> loadIds;
    
    public List<String> getLoadIds() {
        return loadIds;
    }
    
    public void setLoadIds(List<String> loadIds) {
        this.loadIds = loadIds;
    }
}

