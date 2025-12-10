package com.delhivery.TMS_WMS.pojo.picklist.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PicklistSummary {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("status")
    private String status;

    public Integer getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }
}


