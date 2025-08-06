package com.delhivery.Express.pojo.es.PackageCount.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class PackageCountResPayload {
    @JsonProperty("msg")
    private String message;

    @JsonProperty("counts")
    private String counts;
}
