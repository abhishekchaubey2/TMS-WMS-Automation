package com.delhivery.TMS_WMS.pojo.wmsoutbound.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssignPicklistRequest {
    @JsonProperty("pick_wave_id")
    public Integer pickWaveId;
    @JsonProperty("pick_ids")
    public List<Integer> pickIds;
    @JsonProperty("assign")
    public Boolean assign;
    @JsonProperty("user")
    public String user;
}

