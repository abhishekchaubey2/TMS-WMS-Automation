package com.delhivery.Express.pojo.PushWBNToSorter.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class PushWBNToSorterResPayload {
    @JsonProperty("taskid")
    private String upl;
}
