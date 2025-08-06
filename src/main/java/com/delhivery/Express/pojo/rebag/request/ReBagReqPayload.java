package com.delhivery.Express.pojo.rebag.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReBagReqPayload {
    @JsonProperty("data")
    private List<ReBagDataReqPayload> reBagDataReqPayloads;
}
