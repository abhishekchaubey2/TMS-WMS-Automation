package com.delhivery.project1.pojo.module.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContainerCreateRequest {

    @JsonProperty("level")
    private String level;
    @JsonProperty("length")
    private int length;
    @JsonProperty("velocity")
    private String velocity;
    @JsonProperty("aisle")
    private String aisle;
    @JsonProperty("volume")
    private int volume;
    @JsonProperty("fulfillment_center_name")
    private String fulfillmentCenterName;
    @JsonProperty("zone")
    private String zone;
    @JsonProperty("width")
    private int width;
    @JsonProperty("zone_type")
    private String zoneType;
    @JsonProperty("column")
    private String column;
    @JsonProperty("container_id")
    private String containerId;
    @JsonProperty("status")
    private Object status;
    @JsonProperty("height")
    private int height;
    @JsonProperty("container_type")
    private String containerType;
    @JsonProperty("is_movable")
    private boolean isMovable;
    @JsonProperty("audit")
    private boolean audit;
    @JsonProperty("max_wt")
    private float maxWt;
    @JsonProperty("floor")
    private String floor;
}