package com.delhivery.Express.pojo.ms.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class LMDashboardMissingShipmentsRes {
    @JsonProperty("ms")
    private LMDashboardMissingShipmentsDataRes msData;

    @JsonProperty("message")
    private String message;
}
