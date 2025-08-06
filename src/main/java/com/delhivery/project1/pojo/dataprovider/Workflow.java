package com.delhivery.project1.pojo.dataprovider;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Workflow {
    @JsonProperty("noneCancel")
    private String noneCancel;
}