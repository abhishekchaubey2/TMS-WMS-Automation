package com.delhivery.TMS.pojo.contracts.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder @Getter @Setter @NoArgsConstructor @AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RateDetails {
    @JsonProperty("rate") private Double rate;
    @JsonProperty("rateType") private String rateType;
} 