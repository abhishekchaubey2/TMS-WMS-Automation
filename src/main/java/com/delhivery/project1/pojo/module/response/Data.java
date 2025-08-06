package com.delhivery.project1.pojo.module.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Data {

    @JsonProperty("container_id")
    private String containerId;
    @JsonProperty("status")
    private Object status;
    @JsonProperty("sub_order_type")
    private Object subOrderType;
    @JsonProperty("zone")
    private String zone;
    @JsonProperty("inventory_stores")
    private List<Object> inventoryStores = null;
    @JsonProperty("zone_type")
    private Object zoneType;
    @JsonProperty("length")
    private Integer length;
    @JsonProperty("width")
    private Integer width;
    @JsonProperty("height")
    private Integer height;
    @JsonProperty("fulfillment_center_name")
    private String fulfillmentCenterName;
    @JsonProperty("aisle")
    private String aisle;
    @JsonProperty("level")
    private String level;
    @JsonProperty("column")
    private String column;
    @JsonProperty("container_type")
    private String containerType;
    @JsonProperty("is_movable")
    private Boolean isMovable;
    @JsonProperty("audit")
    private Boolean audit;
    @JsonProperty("max_wt")
    private Float maxWt;
    @JsonProperty("floor")
    private String floor;
    @JsonProperty("coordinates")
    private Object coordinates;
    @JsonProperty("last_audit_date")
    private String lastAuditDate;
}


