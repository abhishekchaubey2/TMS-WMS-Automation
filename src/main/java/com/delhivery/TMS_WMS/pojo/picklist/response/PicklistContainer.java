package com.delhivery.TMS_WMS.pojo.picklist.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PicklistContainer {

    @JsonProperty("status")
    private String status;

    @JsonProperty("name")
    private String name;

    public String getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }
}


