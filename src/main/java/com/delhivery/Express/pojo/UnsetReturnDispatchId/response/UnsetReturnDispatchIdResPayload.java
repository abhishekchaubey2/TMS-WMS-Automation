package com.delhivery.Express.pojo.UnsetReturnDispatchId.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class UnsetReturnDispatchIdResPayload {
    @JsonProperty("rmk")
    private String remark;

    @JsonProperty("success")
    private boolean success;
}
