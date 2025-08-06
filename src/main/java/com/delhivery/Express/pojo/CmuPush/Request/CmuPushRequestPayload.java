package com.delhivery.Express.pojo.CmuPush.Request;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "status",
    "shipments",
    "dispatch_date"
})
@Builder
@Getter
@Setter
public class CmuPushRequestPayload {

    @JsonProperty("status")
    public boolean status;
    @JsonProperty("shipments")
    public List<PushShipment> shipments;
    @JsonProperty("dispatch_date")
    public String dispatchDate;

}
