package com.delhivery.Express.pojo.ms.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class LMDashboardMissingShipmentsDataRes {
    @JsonProperty("count")
    private Integer count;

    @JsonProperty("url")
    private String url;
}
