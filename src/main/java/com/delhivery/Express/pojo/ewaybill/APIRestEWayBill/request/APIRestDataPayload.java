package com.delhivery.Express.pojo.ewaybill.APIRestEWayBill.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class APIRestDataPayload {
    @JsonProperty("rs")
    private Integer rs;
    @JsonProperty("ewbn")
    private String eWbn;
    @JsonProperty("dcn")
    private String dcn;
}
