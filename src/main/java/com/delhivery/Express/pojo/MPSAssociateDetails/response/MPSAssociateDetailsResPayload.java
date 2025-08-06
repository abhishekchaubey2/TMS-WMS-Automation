package com.delhivery.Express.pojo.MPSAssociateDetails.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Map;

@Getter
public class MPSAssociateDetailsResPayload {
    @JsonProperty("status")
    private boolean status;

    @JsonProperty("count")
    private int count;

    @JsonProperty("remark")
    private String remark;

    @JsonProperty("result")
    private Map<String, MPSAssociateDetailsResultResPayload> result;
}
