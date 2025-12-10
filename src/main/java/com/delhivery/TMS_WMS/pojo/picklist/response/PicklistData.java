package com.delhivery.TMS_WMS.pojo.picklist.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PicklistData {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("pickwave_id")
    private Integer pickwaveId;

    @JsonProperty("status")
    private String status;

    @JsonProperty("pick_lists")
    private List<PicklistSummary> pickLists;

    @JsonProperty("pick_list_items")
    private List<PicklistItem> pickListItems;

    @JsonProperty("containers")
    private List<PicklistContainer> containers;

    public Integer getId() {
        return id;
    }

    public Integer getPickwaveId() {
        return pickwaveId;
    }

    public String getStatus() {
        return status;
    }

    public List<PicklistSummary> getPickLists() {
        return pickLists;
    }

    public List<PicklistItem> getPickListItems() {
        return pickListItems;
    }

    public List<PicklistContainer> getContainers() {
        return containers;
    }
}


