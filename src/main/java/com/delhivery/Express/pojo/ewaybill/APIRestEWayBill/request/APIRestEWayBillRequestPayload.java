package com.delhivery.Express.pojo.ewaybill.APIRestEWayBill.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class APIRestEWayBillRequestPayload {
    @JsonProperty("data")
    public List<APIRestDataPayload> apiRestDataPayloadList;
}